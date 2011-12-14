package jp.vdmtools.astah.plugin;

import java.awt.FileDialog;

import javax.swing.JOptionPane;

import jp.vdmtools.astah.plugin.util.FileChooseWrapper;

import com.change_vision.astah.extension.plugin.util.AstahAPIUtils;
import com.change_vision.jude.api.inf.exception.LicenseNotFoundException;
import com.change_vision.jude.api.inf.project.ProjectAccessor;
import com.change_vision.jude.api.inf.ui.IPluginActionDelegate;
import com.change_vision.jude.api.inf.ui.IWindow;

public class VDMInAction implements IPluginActionDelegate {

  @Override
  public Object run(IWindow window) throws UnExpectedException {
    AstahAPIUtils apiUtils = new AstahAPIUtils();

    // プロジェクトが開いてたら閉じる
//    try {
//      prjAccessor.getProject();
//      prjAccessor.close();
//    } catch (ProjectNotFoundException e) {
//      // プロジェクトがない場合は何もしない
//      ;
//    } catch (Exception e) {
//      JOptionPane.showMessageDialog(window.getParent(), "Unexpected error has occurred.", "Alert", JOptionPane.ERROR_MESSAGE);
//      throw new UnExpectedException();
//    }

//    FileChooseWrapper fileChooser = new FileChooseWrapper(apiUtils.getMainFrame());
//    FileDialog inputFile = fileChooser.selectDirectoryFile();
//
//    if (!fileChooser.isFileSelected()) {
//      String message = "ファイルを選択して下さい。";
//      JOptionPane.showMessageDialog(window.getParent(), message, "Warning", JOptionPane.WARNING_MESSAGE);
//      return null;
//    }

    // プロジェクトをXML出力
    try {
      // TODO ProjectAccessorを再取得。必要ない？
//      prjAccessor = ProjectAccessorFactory.getProjectAccessor();
//      prjAccessor.create(inputFile.getDirectory());

      ProjectAccessor prjAccessor = apiUtils.getProjectAccessor();
//      prjAccessor.importXMI(inputFile.getDirectory() + inputFile.getFile());
      prjAccessor.importXMI("/Users/mas/Desktop/test.xml");
      prjAccessor.saveAs("/Users/mas/Desktop/test.asta");
    } catch (LicenseNotFoundException e) {
      String message = "This function cannot be used with the Community version. Please use the Professional version.";
      JOptionPane.showMessageDialog(window.getParent(), message, "Warning", JOptionPane.WARNING_MESSAGE);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(window.getParent(), "Unexpected error has occurred.", "Alert", JOptionPane.ERROR_MESSAGE);
      throw new UnExpectedException();
    } catch (Throwable e) {
      JOptionPane.showMessageDialog(window.getParent(), "Unexpected error has occurred.", "Alert", JOptionPane.ERROR_MESSAGE);
      throw new UnExpectedException();
    }

    return null;
  }

}
