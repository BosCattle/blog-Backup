package org.jiangtao.bean.test;

import java.io.Serializable;

/**
 * Created by kevin on 05/11/2016.
 */
public class Image implements Serializable {

  private String title;
  private String image;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public Image(String title, String image) {
    this.title = title;
    this.image = image;
  }
}
