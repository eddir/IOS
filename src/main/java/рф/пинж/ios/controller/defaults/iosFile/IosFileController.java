package рф.пинж.ios.controller.defaults.iosFile;

import рф.пинж.ios.command.CommandSender;
import рф.пинж.ios.controller.Controller;
import рф.пинж.ios.controller.URL;
import рф.пинж.ios.controller.action.CommandAction;
import рф.пинж.ios.model.prototype.iosFile.IosFile;
import рф.пинж.ios.repository.iosFile.IosFileRepository;
import рф.пинж.ios.utils.MainLogger;
import рф.пинж.ios.view.element.Menu;

import java.util.LinkedHashMap;
import java.util.List;

public class IosFileController extends Controller {
    public IosFileController() {
        super(null, null);
    }

    @URL("file")
    public void file (CommandSender sender, String request) {
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
    public void url (CommandSender sender, String request) {
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
}
