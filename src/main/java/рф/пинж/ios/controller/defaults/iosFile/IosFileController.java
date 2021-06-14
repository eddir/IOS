package рф.пинж.ios.controller.defaults.iosFile;

import рф.пинж.ios.Server;
import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.controller.Controller;
import рф.пинж.ios.controller.URL;
import рф.пинж.ios.controller.action.CommandAction;
import рф.пинж.ios.controller.action.InputAction;
import рф.пинж.ios.controller.action.InputableAction;
import рф.пинж.ios.model.prototype.iosFile.IosFile;
import рф.пинж.ios.repository.iosFile.IosFileRepository;
import рф.пинж.ios.utils.MainLogger;
import рф.пинж.ios.view.element.Input;
import рф.пинж.ios.view.element.Menu;

import java.util.LinkedHashMap;

public class IosFileController extends Controller {
    public IosFileController() {
        super(null, null);
    }

    @URL("file")
    public void getFile(CommandSender sender, String request) {
        Menu menu = new Menu(new LinkedHashMap<>() {
            {
                try {
                    IosFile currentFile = (new IosFileRepository()).get(Integer.parseInt(request));

                    put("../" + currentFile.getFile_name(), new CommandAction("openDirectory " + currentFile.getIdDirectory()));
                    put("Удалить файл", new CommandAction("deleteFile " + currentFile.getId()));
                    put("Получить ссылку на файл", new CommandAction("getFile " + currentFile.getId()));
                } catch (Exception e) {
                    MainLogger.getLogger().error(e.getMessage());
                }
            }
        });
        sender.sendView(menu);
    }

    @URL("url")
    public void getUrlFile(CommandSender sender, String request) {
        try {
            IosFile currentFile = (new IosFileRepository()).get(Integer.parseInt(request));

            Menu menu = new Menu(new LinkedHashMap<>() {
                {
                    put("Назад", new CommandAction("openDirectory " + currentFile.getIdDirectory()));
                }
            });
            sender.sendView(menu);
            sender.sendMessage("Url: " + currentFile.getUrlFile());
        } catch (Exception e) {
            MainLogger.getLogger().error(e.getMessage());
        }
    }

    @URL("create")
    public void createFile(CommandSender sender, String request) {
        IosFile file = new IosFile();
        file.setIdDirectory(Integer.parseInt(request));
        new InputAction(new Input(new NewFileInputAction(file, "SET_NAME"), "Введите название файла: ")).execute(sender);
    }

    static class NewFileInputAction implements InputableAction {
        private IosFile file;
        private String dispatch;

        public NewFileInputAction(IosFile file, String dispatch) {
            this.file = file;
            this.dispatch = dispatch;
        }

        @Override
        public void execute(CommandSender sender, String input) throws Exception {
            switch (dispatch) {
                case "SET_NAME":
                    file.setFile_name(input);
                    new InputAction(new Input(new NewFileInputAction(file, "SET_URL"), "Введите url файла: ")).execute(sender);
                    break;
                case "SET_URL":
                    file.setUrlFile(input);
                    new InputAction(new Input(new NewFileInputAction(file, "SAVE_FILE"), "Сохранить файл? 1-да/0-нет: ")).execute(sender);
                    break;
                case "SAVE_FILE":
                    if (Integer.parseInt(input) == 1) {
                        try {
                            (new IosFileRepository()).saveFile(file);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    Server.getInstance().dispatchView(sender, "directory/directory/directory?do=" + file.getIdDirectory());
                    break;
            }
        }
    }
}
