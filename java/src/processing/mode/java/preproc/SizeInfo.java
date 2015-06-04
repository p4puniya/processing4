/* -*- mode: java; c-basic-offset: 2; indent-tabs-mode: nil -*- */

/*
  SizeInfo - parsed elements of a size() or fullScreen() call
  Part of the Processing project - http://processing.org

  Copyright (c) 2015 Ben Fry and Casey Reas

  This program is free software; you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation; either version 2 of the License, or
  (at your option) any later version.

  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with this program; if not, write to the Free Software Foundation,
  Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

package processing.mode.java.preproc;

import processing.app.Base;
import processing.core.PApplet;


public class SizeInfo {
  String statement;
  String width;
  String height;
  String renderer;
  String path;
  String display;


  boolean hasOldSyntax() {
    if (width.equals("screenWidth") ||
        width.equals("screenHeight") ||
        height.equals("screenHeight") ||
        height.equals("screenWidth")) {
      final String message =
        "The screenWidth and screenHeight variables are named\n" +
        "displayWidth and displayHeight in Processing 3.\n" +
        "Or you can use the fullScreen() method instead of size().";
      Base.showWarning("Time for a quick update", message, null);
      return true;
    }
    if (width.equals("screen.width") ||
        width.equals("screen.height") ||
        height.equals("screen.height") ||
        height.equals("screen.width")) {
      final String message =
        "The screen.width and screen.height variables are named\n" +
        "displayWidth and displayHeight in Processing 3.\n" +
        "Or you can use the fullScreen() method instead of size().";
      Base.showWarning("Time for a quick update", message, null);
      return true;
    }
    return false;
  }


  boolean hasBadSize() {
    if (!width.equals("displayWidth") &&
        !width.equals("displayHeight") &&
        PApplet.parseInt(width, -1) == -1) {
      return true;
    }
    if (!height.equals("displayWidth") &&
        !height.equals("displayHeight") &&
        PApplet.parseInt(height, -1) == -1) {
      return true;
    }
    return false;
  }


  void checkEmpty() {
    if (renderer != null) {
      if (renderer.length() == 0) {  // if empty, set null
        renderer = null;
      }
    }
    if (path != null) {
      if (path.length() == 0) {
        path = null;
      }
    }
  }


  public String getStatement() {
    return statement;
  }
}