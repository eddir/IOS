package рф.пинж.ios.permission;

import java.util.List;

public interface Permissible {

    /**
     * Возвращает true, если право присутствует, или false, если право отсутствует
     * @param name название права
     * @return присутствует ли право
     */
    boolean hasPermission(String name);

    /**
     * Возвращает все имеющиеся права
     * @return список прав
     */
    List<String> getEffectivePermissions();

    /**
     * Сбрасывает права до начальных
     */
    void recalculatePermissions();

}
