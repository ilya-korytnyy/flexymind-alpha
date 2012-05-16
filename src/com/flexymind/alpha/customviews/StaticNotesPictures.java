package com.flexymind.alpha.customviews;

import android.graphics.Picture;
import com.flexymind.alpha.R;
import com.flexymind.alpha.StaticResources;
import com.larvalabs.svgandroid.SVGParser;

import static com.flexymind.alpha.customviews.NoteView.*;

public class StaticNotesPictures {
    public static final Picture[] blackPictures;
    public static final Picture[] redPictures;
    public static final Picture[] greenPictures;

    static {
        blackPictures = new Picture[4];

        blackPictures[UP] = new Picture(SVGParser.getSVGFromResource
                (StaticResources.res, R.raw.noteup).getPicture());
        blackPictures[DOWN] = new Picture(SVGParser.getSVGFromResource
                (StaticResources.res, R.raw.notedown).getPicture());
        blackPictures[SHARP_DOWN] = new Picture(SVGParser.getSVGFromResource
                (StaticResources.res, R.raw.sharpnotedown).getPicture());
        blackPictures[SHARP_UP] = new Picture(SVGParser.getSVGFromResource
                (StaticResources.res, R.raw.sharpnoteup).getPicture());

        redPictures = new Picture[4];

        redPictures[UP] = new Picture(SVGParser.getSVGFromResource
                (StaticResources.res, R.raw.noteup).getPicture());
        redPictures[DOWN] = new Picture(SVGParser.getSVGFromResource
                (StaticResources.res, R.raw.notedown).getPicture());
        redPictures[SHARP_DOWN] = new Picture(SVGParser.getSVGFromResource
                (StaticResources.res, R.raw.sharpnotedown).getPicture());
        redPictures[SHARP_UP] = new Picture(SVGParser.getSVGFromResource
                (StaticResources.res, R.raw.sharpnoteup).getPicture());

        greenPictures = new Picture[4];

        greenPictures[UP] = new Picture(SVGParser.getSVGFromResource
                (StaticResources.res, R.raw.noteup).getPicture());
        greenPictures[DOWN] = new Picture(SVGParser.getSVGFromResource
                (StaticResources.res, R.raw.notedown).getPicture());
        greenPictures[SHARP_DOWN] = new Picture(SVGParser.getSVGFromResource
                (StaticResources.res, R.raw.sharpnotedown).getPicture());
        greenPictures[SHARP_UP] = new Picture(SVGParser.getSVGFromResource
                (StaticResources.res, R.raw.sharpnoteup).getPicture());
    }


}
