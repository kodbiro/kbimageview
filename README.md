# KBImageView
KBImageView is custom ImageView for Android and allows you to display images in circular shape, with shadow and 
border. You can easy add border or shadow and after that you can customize them as you want. 
See how it works in several screenshots.

# Screenshots
<img src="https://github.com/kodbiro/kodbiro.github.io/blob/master/assets/images/KBImageView_readme1.png?raw=true" width="280" height="490" />
<img src="https://github.com/kodbiro/kodbiro.github.io/blob/master/assets/images/KBImageView_readme2.png?raw=true" width="280" height="490" />
<img src="https://github.com/kodbiro/kodbiro.github.io/blob/master/assets/images/KBImageView_readme3.png?raw=true" width="280" height="490" />
<img src="https://github.com/kodbiro/kodbiro.github.io/blob/master/assets/images/KBImageView_readme4.png?raw=true" width="280" height="490" />
<img src="https://github.com/kodbiro/kodbiro.github.io/blob/master/assets/images/KBImageView_readme5.png?raw=true" width="280" height="490" />

# Download

Download as a gradle dependency in your project. To use it you will have to edit your gradle files in two places. First you need to add new maven repository to your top level gradle file. The repository is a link to your GitHub repository created in step one. Here's what you need to add to use KBImageView library:

```gradle
repositories {
  maven {
    url "http://kodbiro.github.io/repo/"
  }
}
```

Next you need to add reference to your library that you uploaded in step three. Something like this:

```gradle
dependencies {
  ...
  compile 'com.kodbiro.kbimageview:kbimageview:1.0@aar'
}
```

That's it. Now you can use KBImageView inside your project.

# Usage

In xml layout add something like this:

```xml
  <com.kodbiro.kbimageview.KBImageView
        android:id="@+id/kbimageview"
        android:layout_width="120dp"
        android:layout_height="120dp"
        android:src="@drawable/example_photo" />
```
Example how to use in activity:

```Java
  KBImageView kbImageView = (KBImageView) findViewById(R.id.kbimageview);
  
  kbImageView.setSizeToFit(true); 
  //KBImageView is automatically resized that image fits perfectly
  
  kbImageView.setImageViewShape(KBImageView.ImageViewShape.CIRCLE); 
  //KBImageView will have circular shape. Default is RECTANGLE shape
```
Setup border:
```Java
  kbImageView.setBorderVisible(true);         //Default border is not visible
  kbImageView.setBorderColor(Color.BLACK);    //Default border color is WHITE
  kbImageView.setBorderWidth(6.0f);           //Default border width is 1.0f
  
  kbImageView.setBorderTexture(BitmapFactory.decodeResource(getResources(), R.drawable.texture));
  //Set border texture to replace border color
```

Setup shadow:

```Java
  kbImageView.setShadowVisible(true);         //Default shadow is not visible
  kbImageView.setShadowRadius(8.0f);          //Default shadow radius is 6.0f
  kbImageView.setShadowColor(Color.RED);      //Default shadow color is BLACK
```
