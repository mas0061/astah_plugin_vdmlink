package com.change_vision.astah.extension.plugin.util;

import javax.swing.JFrame;

import com.change_vision.jude.api.inf.exception.InvalidUsingException;
import com.change_vision.jude.api.inf.project.ProjectAccessor;
import com.change_vision.jude.api.inf.project.ProjectAccessorFactory;

/**
 * astah* APIを扱いやすいように包んだユーティリティクラスです。
 * 利用時にインスタンスを作成してください。
 */
public class AstahAPIUtils {

  /**
   * astah*のプロジェクトを扱うためのProjectAccessorを取得します。
   */
  public ProjectAccessor getProjectAccessor() {
    ProjectAccessor projectAccessor = null;
    try {
      projectAccessor = ProjectAccessorFactory.getProjectAccessor();
    } catch (ClassNotFoundException e) {
          throw new IllegalStateException(e);
    }
    if(projectAccessor == null) throw new IllegalStateException("projectAccessor is null.");
    return projectAccessor;
  }

  /**
   * astah*本体のメインウィンドウに対応するJFrameを取得します。
   *
   * @return JFrame
   */
  public JFrame getMainFrame() {
    try {
      return getProjectAccessor().getViewManager().getMainFrame();
    } catch (InvalidUsingException e) {
      throw new IllegalStateException(e);
    }
  }

}