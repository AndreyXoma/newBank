package utils;

public class UserMenu implements Menu{
    @Override
    public void printMenu() {
        System.out.println(
                """
                        1. Просмотреть баланс
                        2. Перевести деньги другому пользователю
                        3. Просмотреть информацию о себе
                        4. Изменить информацию о себе
                        5. История переводов
                        6. Выход
                        """
        );
    }
}
