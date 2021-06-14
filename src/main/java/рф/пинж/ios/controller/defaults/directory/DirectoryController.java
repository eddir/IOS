package рф.пинж.ios.controller.defaults.directory;

import рф.пинж.ios.Server;
import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.controller.Controller;
import рф.пинж.ios.controller.URL;
import рф.пинж.ios.controller.action.CommandAction;
import рф.пинж.ios.controller.action.InputAction;
import рф.пинж.ios.controller.action.InputableAction;
import рф.пинж.ios.model.prototype.directory.Directory;
import рф.пинж.ios.model.prototype.iosFile.IosFile;
import рф.пинж.ios.repository.directory.DirectoryRepository;
import рф.пинж.ios.repository.iosFile.IosFileRepository;
import рф.пинж.ios.utils.MainLogger;
import рф.пинж.ios.view.element.Input;
import рф.пинж.ios.view.element.Menu;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public class DirectoryController extends Controller {
    public DirectoryController() {
        super(null, null);
    }

    @URL("directory")
    public void directory(CommandSender sender, String request) {
        Menu menu = new Menu(new LinkedHashMap<>() {
            {
                try {
                    Directory currentDir = (new DirectoryRepository()).get(Integer.parseInt(request));
                    List<Directory> childrenDir = (new DirectoryRepository()).getChildren(Integer.parseInt(request));
                    List<IosFile> files = (new IosFileRepository()).getNestedFiles(Integer.parseInt(request));

                    put("../" + currentDir.getTitle(), currentDir.getParent_dir() != 0 ?
                            new CommandAction("openDirectory " + currentDir.getParent_dir()) :
                            new CommandAction("openDirectory 1")); //TODO: переход на дисциплину

                    for (Directory child : childrenDir) {
                        put("./" + child.getTitle(), new CommandAction("openDirectory " + child.getId()));
                    }

                    for (IosFile file : files) {
                        put(file.getFile_name(), new CommandAction("openFile " + file.getId()));
                    }

                    if (currentDir.getParent_dir() != 0) {
                        put("Удалить папку", new CommandAction("deleteDirectory " + currentDir.getId()));
                    }
                    put("Добавить папку", new CommandAction("createDirectory " + currentDir.getId()));
                    put("Добавить файл", new CommandAction("createFile " + currentDir.getId()));
                } catch (Exception e) {
                    MainLogger.getLogger().error(e.getMessage());
                }
            }
        });
        sender.sendView(menu);
    }

    @URL("create")
    public void createDirectory(CommandSender sender, String request) {
        Directory dir = new Directory();
        dir.setParent_dir(Integer.parseInt(request));
        dir.setCreated_at(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        new InputAction(new Input(new NewDirectoryInputAction(dir, "SET_DIRNAME"), "Введите название директории: ")).execute(sender);
    }

    static class NewDirectoryInputAction implements InputableAction {
        private Directory dir;
        private String dispatch;

        public NewDirectoryInputAction(Directory dir, String dispatch) {
            this.dir = dir;
            this.dispatch = dispatch;
        }

        @Override
        public void execute(CommandSender sender, String input) throws Exception {
            switch (dispatch) {
                case "SET_DIRNAME":
                    dir.setTitle(input);
                    new InputAction(new Input(new DirectoryController.NewDirectoryInputAction(dir, "SAVE_DIR"), "Сохранить директорию? 1-да/0-нет: ")).execute(sender);
                    break;
                case "SAVE_DIR":
                    if (Integer.parseInt(input) == 1) {
                        try {
                            (new DirectoryRepository()).saveDirectory(dir);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    Server.getInstance().dispatchView(sender, "directory/directory/directory?do=" + dir.getParent_dir());
                    break;
            }
        }
    }

}
