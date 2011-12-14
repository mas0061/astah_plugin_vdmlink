package jp.vdmtools.astah.plugin;

import javax.swing.JOptionPane;

import jp.vdmtools.astah.plugin.util.FileChooseWrapper;

import com.change_vision.astah.extension.plugin.util.AstahAPIUtils;
import com.change_vision.jude.api.inf.exception.LicenseNotFoundException;
import com.change_vision.jude.api.inf.exception.ProjectNotFoundException;
import com.change_vision.jude.api.inf.project.ProjectAccessor;
import com.change_vision.jude.api.inf.ui.IPluginActionDelegate;
import com.change_vision.jude.api.inf.ui.IWindow;

public class VDMOutAction implements IPluginActionDelegate {

	@Override
	public Object run(IWindow window) throws UnExpectedException {
	  AstahAPIUtils apiUtils = new AstahAPIUtils();
    ProjectAccessor prjAccessor = apiUtils.getProjectAccessor();

	  try {
	    prjAccessor.getProject();
	  } catch (ProjectNotFoundException e) {
	    String message = "Project is not opened.Please open the project or create new project.";
	    JOptionPane.showMessageDialog(window.getParent(), message, "Warning", JOptionPane.WARNING_MESSAGE);
	  } catch (Exception e) {
	  	JOptionPane.showMessageDialog(window.getParent(), "Unexpected error has occurred.", "Alert", JOptionPane.ERROR_MESSAGE);
	    throw new UnExpectedException();
	  }

    // 出力するファイルの場所とファイル名を指定
    FileChooseWrapper fileChoose = new FileChooseWrapper(apiUtils.getMainFrame());
    String fileName = fileChoose.saveFile();

    if (!fileChoose.isFileSelected()) {
      String message = "ファイルを選択して下さい。";
      JOptionPane.showMessageDialog(window.getParent(), message, "Warning", JOptionPane.WARNING_MESSAGE);
    } else {
      // プロジェクトをXML出力
      try {
        prjAccessor.exportXMI(fileName);
      } catch (LicenseNotFoundException e) {
        String message = "This function cannot be used with the Community version. Please use the Professional version.";
        JOptionPane.showMessageDialog(window.getParent(), message, "Warning", JOptionPane.WARNING_MESSAGE);
      } catch (Exception e) {
        JOptionPane.showMessageDialog(window.getParent(), "Unexpected error has occurred.", "Alert", JOptionPane.ERROR_MESSAGE);
        throw new UnExpectedException();
      }
    }

	  return null;
	}
}
