package рф.пинж.ios.controller.defaults.directory;

import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.controller.Controller;
import рф.пинж.ios.controller.URL;
import рф.пинж.ios.controller.action.CommandAction;
import рф.пинж.ios.model.prototype.directory.Directory;
import рф.пинж.ios.model.prototype.iosFile.IosFile;
import рф.пинж.ios.repository.directory.DirectoryRepository;
import рф.пинж.ios.repository.iosFile.IosFileRepository;
import рф.пинж.ios.utils.MainLogger;
import рф.пинж.ios.view.element.Menu;

import java.util.LinkedHashMap;
import java.util.List;

public class DirectoryController extends Controller {
    public DirectoryController() {
        super(null, null);
    }

    @URL("directory")
    public void directory (CommandSender sender, String request) {
        Menu menu = new Menu(new LinkedHashMap<>() {
            {
                try {
                    Directory currentDir = (new DirectoryRepository()).get(Integer.parseInt(request));
                    List<Directory> childrenDir = (new DirectoryRepository()).getChildren(Integer.parseInt(request));
                    List<IosFile> files = (new IosFileRepository()).getNestedFiles(Integer.parseInt(request));

                    put("../" + currentDir.getTitle(), currentDir.getParent_dir() != null ?
                            new CommandAction("openDirectory " + currentDir.getParent_dir()) :
                            new CommandAction("openDirectory 1")); //TODO: переход на дисциплину

                    for (Directory child : childrenDir) {
                        put("./" + child.getTitle(), new CommandAction("openDirectory " + child.getId()));
                    }

                    for (IosFile file : files) {
                        put(file.getFile_name(), new CommandAction("openFile " + file.getId()));
                    }

                    put("Удалить папку", null);
                    put("Добавить папку", null);
                    put("Добавить файл", new CommandAction("setFile " + currentDir.getId()));
                } catch (Exception e) {
                    MainLogger.getLogger().error(e.getMessage());
                }
            }
        });
        sender.sendView(menu);
    }
}
