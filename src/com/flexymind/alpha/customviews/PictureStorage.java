package com.flexymind.alpha.customviews;

import android.graphics.Picture;
import com.flexymind.alpha.R;
import com.larvalabs.svgandroid.SVGParser;

import static com.flexymind.alpha.StaticResources.*;
import static com.flexymind.alpha.customviews.NoteView.*;

public class PictureStorage {
    public static final Picture[] blackPictures;
    public static final Picture[] redPictures;
    public static final Picture[] greenPictures;

    public static final Picture   linePicture;
    public static final Picture   whiteKeyNotPressed;
    public static final Picture   blackKeyNotPressed;
    public static final Picture   clefPicture;
    public static final Picture   startButton;
    public static final Picture   pressedStartButton;


    static {
        blackPictures = new Picture[4];

        blackPictures[UP] = new Picture(SVGParser.getSVGFromResource
                (res, R.raw.noteup).getPicture());
        blackPictures[DOWN] = new Picture(SVGParser.getSVGFromResource
                (res, R.raw.notedown).getPicture());
        blackPictures[SHARP_DOWN] = new Picture(SVGParser.getSVGFromResource
                (res, R.raw.sharpnotedown).getPicture());
        blackPictures[SHARP_UP] = new Picture(SVGParser.getSVGFromResource
                (res, R.raw.sharpnoteup).getPicture());

        redPictures = new Picture[4];

        redPictures[UP] = new Picture(SVGParser.getSVGFromResource
                (res, R.raw.noteup, 0x000000, 0xFF0000).getPicture());
        redPictures[DOWN] = new Picture(SVGParser.getSVGFromResource
                (res, R.raw.notedown, 0x000000, 0xFF0000).getPicture());
        redPictures[SHARP_DOWN] = new Picture(SVGParser.getSVGFromResource
                (res, R.raw.sharpnotedown, 0x000000, 0xFF0000).getPicture());
        redPictures[SHARP_UP] = new Picture(SVGParser.getSVGFromResource
                (res, R.raw.sharpnoteup, 0x000000, 0xFF0000).getPicture());

        greenPictures = new Picture[4];

        greenPictures[UP] = new Picture(SVGParser.getSVGFromResource
                (res, R.raw.noteup, 0x000000, 0x00AA00).getPicture());
        greenPictures[DOWN] = new Picture(SVGParser.getSVGFromResource
                (res, R.raw.notedown, 0x000000, 0x00AA00).getPicture());
        greenPictures[SHARP_DOWN] = new Picture(SVGParser.getSVGFromResource
                (res, R.raw.sharpnotedown, 0x000000, 0x00AA00).getPicture());
        greenPictures[SHARP_UP] = new Picture(SVGParser.getSVGFromResource
                (res, R.raw.sharpnoteup, 0x000000, 0x00AA00).getPicture());

        linePicture = SVGParser.getSVGFromResource( res
                                                  , R.raw.line).getPicture();

        whiteKeyNotPressed =
                SVGParser.getSVGFromResource( res
                                            , R.raw.whitekey).getPicture();
        blackKeyNotPressed =
                SVGParser.getSVGFromResource( res
                                            , R.raw.blackkey).getPicture();

        clefPicture =
                SVGParser.getSVGFromResource( res
                                            , R.raw.scaledclef).getPicture();

        startButton =
                SVGParser.getSVGFromResource( res
                                            , R.raw.startbutton).getPicture();

        pressedStartButton =
                SVGParser.getSVGFromResource( res
                                            , R.raw.pressedstartbutton).getPicture();
    }
}
