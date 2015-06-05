package com.kodbiro.kbimageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Danijel Kunkic on 7.5.2015
 *
 * KBImageView class is custom ImageView class for displaying image
 * Provides custom shadow around image and custom border
 * Can display image in rectangular and circular shape
 */

@SuppressWarnings("SuspiciousNameCombination")
public class KBImageView extends ImageView {


    public enum ImageViewShape {
        RECTANGLE,
        CIRCLE
    }

    private int viewWidth;
    private int viewHeight;

    //border default
    private boolean isBorderVisible = false;
    private float borderWidth = 1.0f;
    private Paint borderPaint;

    //shadow default
    private boolean isShadowVisible = false;
    private float shadowRadius = 6.0f;
    private Paint shadowPaint;
    private int shadowColor = Color.BLACK;

    private Paint clearPaint;
    private Paint clearStrokePaint;
    private ImageViewShape imageViewShape;

    private boolean sizeToFit = false;


    /**
     * Gets current image view shape
     * @return image view shape
     */
    public ImageViewShape getImageViewShape() {
        return imageViewShape;
    }


    /**
     * Sets the shape of the image view
     * @param imageViewShape image view shape
     */
    public void setImageViewShape(ImageViewShape imageViewShape) {
        this.imageViewShape = imageViewShape;
        this.invalidate();
    }


    /**
     * Size to fit
     * @return size to fit
     */
    public boolean isSizeToFit() {
        return sizeToFit;
    }

    /**
     * Sets sizeToFit property. If true, KBImageView will automatically be resized to fit its content
     * @param sizeToFit SizeToFit
     */
    public void setSizeToFit(boolean sizeToFit) {
        this.sizeToFit = sizeToFit;
        this.invalidate();
    }



    /**
     * border visibility
     * @param borderVisible    if this is true border will show with default border width and white color
     */
    public void setBorderVisible(boolean borderVisible) {
        this.isBorderVisible = borderVisible;
        this.invalidate();
    }

    /**
     * set border width
     * @param borderWidth default value is 1.0
     */
    public void setBorderWidth(float borderWidth) {
        this.borderWidth = borderWidth;
        this.borderPaint.setStrokeWidth(borderWidth);
        this.shadowPaint.setShadowLayer(shadowRadius, 2.0f, 2.0f, this.shadowColor);
        this.invalidate();
    }

    /**
     * set border color direct to border paint
     * @param borderColor resource color
     */
    public void setBorderColor(int borderColor) {
        borderPaint.setColor(borderColor);
        this.invalidate();
    }

    /**
     * set border textures from bitmap, shader will be create with repeat tileX and tileY
     * @param bitmap texture
     */
    public void setBorderTexture(Bitmap bitmap){
        borderPaint.setShader(new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));
        this.invalidate();
    }

    /**
     * shadow visibility
     * @param shadowVisible if this is true shadow will show with default radius and black color
     */
    public void setShadowVisible(boolean shadowVisible) {
        this.isShadowVisible = shadowVisible;
        int p = (int)this.shadowRadius;
        this.setPadding(p,p,p,p);
        this.invalidate();
    }

    /**
     * set shadow radius
     * @param shadowRadius default size is 6.0f
     */
    public void setShadowRadius(float shadowRadius) {
        this.shadowRadius = shadowRadius;
        int p = (int)this.shadowRadius;
        this.setPadding(p,p,p,p);
        this.invalidate();
    }

    /**
     * set shadow color direct to shadow color
     * @param shadowColor resource color
     */
    public void setShadowColor(int shadowColor) {
        this.shadowColor = shadowColor;
        this.shadowPaint.setShadowLayer(shadowRadius, 2.0f, 2.0f, shadowColor);
        this.invalidate();
    }


    public KBImageView(Context context) {
        super(context);
        setup();
    }

    public KBImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public KBImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup();
    }

    private void setup() {

        //create shader paint for circular ImageView
        //shader used to draw a bitmap as a texture
        this.clearPaint = new Paint();
        this.clearPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        this.clearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));


        this.clearStrokePaint = new Paint();
        this.clearStrokePaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        this.clearStrokePaint.setStyle(Paint.Style.STROKE);
        this.clearStrokePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

        //create paint for drawing border
        this.borderPaint = new Paint();
        this.setBorderColor(Color.WHITE);
        this.borderPaint.setStyle(Paint.Style.STROKE);
        this.borderPaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        //create paint for drawing shadow
        this.shadowPaint = new Paint();
        this.shadowPaint.setColor(Color.WHITE);
        this.shadowPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        this.shadowPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));
        //we need to enable hardware acceleration for this view for drawing shadow
        this.setLayerType(LAYER_TYPE_SOFTWARE, null);

        //This draws a shadow layer below the main layer, with the specified
        //offset and color, and blur radius. If radius is 0, then the shadow
        //layer is removed.
        this.shadowPaint.setShadowLayer(shadowRadius, 2.0f, 2.0f, this.shadowColor);

        this.imageViewShape = ImageViewShape.RECTANGLE;
    }


    private Path createUpperPath() {
        Path path = new Path();

        float rectX = getPaddingLeft();
        float rectY = getPaddingTop();

        if (getWidth() > getHeight()) {
            rectX = (getWidth()-getHeight())/2.0f+getPaddingLeft();
        } else {
            rectY = (getHeight()-getWidth())/2.0f+getPaddingTop();
        }

        path.moveTo(getWidth()-getPaddingRight(), getHeight()/2.0f);
        path.lineTo(getWidth()-getPaddingRight(), getPaddingTop());
        path.lineTo(getPaddingLeft(), getPaddingTop());
        path.lineTo(getPaddingLeft(), getHeight()/2.0f);
        RectF arcRect = new RectF(rectX, rectY, getWidth()-rectX, getHeight()-rectY);
        path.arcTo(arcRect, -180, 180);
        path.lineTo(getWidth()-getPaddingRight(), getHeight()/2.0f);
        return path;
    }

    private Path createLowerPath() {
        Path path = new Path();

        float rectX = getPaddingLeft();
        float rectY = getPaddingTop();

        if (getWidth() > getHeight()) {
            rectX = (getWidth()-getHeight())/2.0f+getPaddingLeft();
        } else {
            rectY = (getHeight()-getWidth())/2.0f+getPaddingTop();
        }

        path.moveTo(getWidth()-getPaddingRight(), getHeight()/2.0f);
        path.lineTo(getWidth()-getPaddingRight(), getHeight()-getPaddingBottom());
        path.lineTo(getPaddingLeft(), getHeight()-getPaddingBottom());
        path.lineTo(getPaddingLeft(), getHeight()/2.0f);
        RectF arcRect = new RectF(rectX, rectY, getWidth()-rectX, getHeight()-rectY);
        path.arcTo(arcRect, -180, -180);
        path.lineTo(getWidth()-getPaddingLeft(), getHeight()/2.0f);
        return path;
    }


    private Path createEnclosingPath(float borderWidth) {
        Path path = new Path();
        if (this.imageViewShape == ImageViewShape.CIRCLE) {
            path.addCircle(getWidth()/2.0f, getHeight()/2.0f, getWidth() < getHeight() ? getWidth()/2.0f-borderWidth/2.0f : getHeight()/2.0f-borderWidth/2.0f, Path.Direction.CW);
        } else {
            path.addRect(borderWidth/2.0f,borderWidth/2.0f, getWidth()-borderWidth/2.0f, getHeight()-borderWidth/2.0f, Path.Direction.CW);
        }
        return path;
    }


    @Override
    public void onDraw(@NonNull Canvas canvas) {

        super.onDraw(canvas);
        if (this.imageViewShape == ImageViewShape.CIRCLE) {
            canvas.drawPath(createUpperPath(), this.clearPaint);
            canvas.drawPath(createLowerPath(), this.clearPaint);
        }

        if (isShadowVisible) {
            canvas.drawPath(createEnclosingPath(this.shadowRadius*2), this.shadowPaint);
        }
        if (isBorderVisible) {
            this.clearStrokePaint.setStrokeWidth(this.borderWidth);
            canvas.drawPath(createEnclosingPath(this.borderWidth), this.clearStrokePaint);
            canvas.drawPath(createEnclosingPath(this.borderWidth), this.borderPaint);
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!sizeToFit) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        Drawable drawable = getDrawable();
        if (drawable != null) {
            //calculate image ration
            float imageSideRatio = (float) drawable.getIntrinsicWidth() / (float) drawable.getIntrinsicHeight();
            //calculate view ratio
            float viewSideRatio = (float) MeasureSpec.getSize(widthMeasureSpec) / (float) MeasureSpec.getSize(heightMeasureSpec);

            if (this.imageViewShape != ImageViewShape.CIRCLE){
                measuredDimensionForRectangle(imageSideRatio, viewSideRatio, widthMeasureSpec, heightMeasureSpec);
            } else {
                measuredDimensionForCircle(imageSideRatio, widthMeasureSpec, heightMeasureSpec);
            }
        } else {
            setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
        }
    }

    /**
     * set new measured width and height of this view
     * circle view
     * width and height need to be same size, smaller measured size will apply
     *
     * @param imageSideRatio drawable width / height
     * @param widthMeasureSpec the measured width of this view
     * @param heightMeasureSpec the measured height of this view
     */
    private void measuredDimensionForCircle(float imageSideRatio, int widthMeasureSpec, int heightMeasureSpec) {
        if (MeasureSpec.getSize(widthMeasureSpec) <= MeasureSpec.getSize(heightMeasureSpec)){
            viewWidth = MeasureSpec.getSize(widthMeasureSpec);
            viewHeight = (int) (viewWidth / imageSideRatio);
            setMeasuredDimension(viewWidth, viewWidth);
        } else {
            viewHeight = MeasureSpec.getSize(heightMeasureSpec);
            viewWidth = (int) (viewHeight * imageSideRatio);
            setMeasuredDimension(viewHeight, viewHeight);
        }
    }

    /**
     * set new measured width and height of this view
     * rectangular view
     *
     * @param imageSideRatio drawable width / height
     * @param viewSideRatio view width / height
     * @param widthMeasureSpec the measured width of this view
     * @param heightMeasureSpec the measured height of this view
     */
    private void measuredDimensionForRectangle(float imageSideRatio, float viewSideRatio, int widthMeasureSpec, int heightMeasureSpec) {
        if (imageSideRatio >= viewSideRatio) {
            viewWidth = MeasureSpec.getSize(widthMeasureSpec);
            viewHeight = (int) (viewWidth / imageSideRatio);
            setMeasuredDimension(viewWidth, viewHeight);
        } else {
            viewHeight = MeasureSpec.getSize(heightMeasureSpec);
            viewWidth = (int) (viewHeight * imageSideRatio);
            setMeasuredDimension(viewWidth, viewHeight);
        }
    }
}
