package utils;

public class AdminMenu implements Menu{
    @Override
    public void printMenu() {
        System.out.println(
                """
                        1. Просмотреть список пользователей
                        2. Добавить нового пользователя
                        3. Просмотреть информацию о себе
                        4. Изменить данные о себе
                        5. Выход
                        """
        );
    }
}
