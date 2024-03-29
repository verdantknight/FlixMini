﻿# FlixMini
Небольшое Android-приложение для загрузки и отображения информации о фильмах с TMDB.

# Процесс создания
Первым делом - составить список, какие функции нужны в итоговом приложении. Что я понял из документации:
<p align="center">
<img src="https://raw.githubusercontent.com/verdantknight/FlixMini/master/img/usecase.jpg" alt="" /><br />
<i>
Use case diagram
</i>
</p>

## 1. Создание первого приложения
Для начала, я сделал простой список (List View) с отображением загруженных названий.
<p align="center">
<img width="180" src="https://raw.githubusercontent.com/verdantknight/FlixMini/master/img/interfacebeta.jpg" alt="" /><br />
<i>
Первый вариант интерфейса
</i>
</p>
Это позволило освоиться с созданием проекта в Android и примерно понять, от чего отталкиваться. 
Всё приложение - пользовательский интерфейс и написанный в лучших традициях плохого кода вросший в него обработчик от Retrofit:
<p align="center">
<img src="https://raw.githubusercontent.com/verdantknight/FlixMini/master/img/classes.jpg" alt="" /><br />
<i>
Схема первого созданного приложения
</i>
</p>
Приложение загружает данные с REST API и показывает их пользователю. Сущности, которые Retrofit достаёт из REST API (их достаточно для всего будущего проекта):
<p align="center">
<img src="https://raw.githubusercontent.com/verdantknight/FlixMini/master/img/entities.jpg" alt="" /><br />
<i>
Entities
</i>
</p>
Они соответствуют API:

```json
{
  "page": 1,
  "results": [
      {}
  ]
}
```

## 2. RecyclerView
Затем переделал ListView в RecyclerView, добавив больше информации и сделав интерфейс ближе к поставленной задаче:
<p align="center">
<img width="180" src="https://raw.githubusercontent.com/verdantknight/FlixMini/master/img/interfacebeta1.jpg" alt="" /><br />
<i>
Новый интерфейс с RecyclerView
</i>
</p>
В RecyclerView сделал элемент списка по заданию. Добавил картинку календарика, лайка. Сделал точные размеры текстовых элементов и отступов.
Сама вёрстка - не самый сложный процесс, но всегда уходит довольно много времени, чтобы итоговый продукт соответствовал макету. Так как пока постеров нет, элементы списка чуть меньше по высоте, чем будут в итоговом варианте:
<p align="center">
<img width="180" src="https://raw.githubusercontent.com/verdantknight/FlixMini/master/img/interfacebeta2.jpg" alt="" /><br />
<i>
Пару часов убил на вёрстку пиксель в пиксель
</i>
</p>

Так как на экране в требованиях к приложению формат даты другой, сделаю форматирование даты в показанный там формат.
Локализация даты лежит в сервисе `DateLocalizationService`. Для него даже написан единственный (пока) в приложении тест:

```java
public class DateLocalizationServiceTest {
    @Test
    public void localizeDate() {
        DateLocalizationService dateLocalizationService = new DateLocalizationService();
        assertEquals("1 июня 2019", dateLocalizationService.localizeDate("2019-06-01"));
    }
}
```

Локализация реализована не лучшим образом (сделал всё под русский язык, в случае введения поддержки других языков нужно будет менять код).
Хотя я вытянул всю локализацию в отдельные классы (`DateLocalizationService`, `Constants`), чтобы легче было позже исправить.

## 3. Точная вёрстка
Далее - занялся серьёзной вёрсткой макета по образцу. Результат вышел с незначительными отличиями, но эти же отличия присутствуют и в образцах в задании.
На вёрстку опять ушло некоторое количество часов. Иллюстрация:
<p align="center">
<img src="https://raw.githubusercontent.com/verdantknight/FlixMini/master/img/interface.jpg" alt="" /><br />
<i>
"Найди пять отличий": один из экранов - из задания, другой - результат работы
</i>
</p>
Основа приложения готова. Остаётся перерефакторить это всё в нормальную архитектуру, чтобы позже добавлять новые функции было проще.

## 4. MVP
В одной Activity реализовать MVP достаточно просто, но нужно написать свой логгер, чтобы полностью удалить код, связанный с android,
из MovieListPresenter. Этот же код можно будет использовать в других частях приложения, но перед этим желательно перевести проект на 
Dagger, чтобы позже не переделывать слишком многого.

<p align="center">
<img src="https://raw.githubusercontent.com/verdantknight/FlixMini/master/img/MVPclasses.jpg" alt="" /><br />
<i>
Классы, реализующие MVP
</i>
</p>

MovieListPresenter ничего не знает о MovieListActivity, а только использует MovieListContract.View.
Таким образом, MovieListPresenter становится полностью независим от платформы Android.
Код MovieListContract.View состоит из небольшого изолирующего интерфейса:

```java
public interface MovieListContract {
    interface View {
        void showPage(PageEntity page);
    }
}
```

На данный момент, приложение состоит из большего числа классов, чем раньше:

<p align="center">
<img src="https://raw.githubusercontent.com/verdantknight/FlixMini/master/img/projectclasses.jpg" alt="" /><br />
<i>
Основные классы приложения на данном этапе
</i>
</p>

## 5. Поиск фильмов

Поиск фильмов реализовывать было уже проще, потому что методы похожи на те, что используются в
главном списке, и проект настроен и работает.

<p align="center">
<img src="https://raw.githubusercontent.com/verdantknight/FlixMini/master/img/interfacesearch.jpg" alt="" /><br />
<i>
Поиск почему-то выдал один и тот же фильм, но с разными постерами, описаниями и датами
</i>
</p>

В задании почему-то написано "Кнопку “очистить(крестик)” в строке поиска реализовывать не нужно", хотя 
это по сути приводит к застреванию пользователя в экране поиска.\
На всякий случай сделал возможность для пользователя получить основной список просто по пустой строке:

```java
public class MovieListPresenter {
    public void loadMovieList() {
        // Загрузка обычного экрана
    }
    public void search(String query) {
        if (query.isEmpty()) {
            loadMovieList();
        } else {
            // Поиск фильмов
        }
    }
}
```

<p align="center">
<i>
<b>
To be continued...
</b>
</i>
</p>


# Технические характеристики
#### Поддержка версий: Android 5.0+
Обоснование: требования в задании
#### Поддерживаемые языки: **русский**
Обоснование: требования в задании
#### Язык реализации: **Java**
Обоснование: уже знаю Java, Kotlin'ом (почти) не занимался, так что для быстроты реализации лучше Java.
#### Подход к архитектуре: **MVP**
<p align="center">
<img width="180" src="https://raw.githubusercontent.com/verdantknight/FlixMini/master/img/MVP.jpg" alt="" /><br />
<i>
Приложение построено по MVP
</i>
</p>

# Работа над приложением
## TODO
###### Напишу заранее список задач, по которому делаю приложение. Некоторые функции могу не успеть сделать.
~~Просто отображение названия в списке~~\
~~Заменить List View на Recycler View~~\
~~Отображение описания в списке~~\
~~Отображение даты выхода в списке~~\
~~Локализация даты~~\
~~Отображение постеров в списке~~\
~~Точное соответствие вёрстки дизайну из задания~~\
~~**Паттерн MVP**~~\
~~Поиск фильмов~~\
Поиск фильмов: pull to refresh\
Поиск фильмов: обработка ошибки\
Лайк фильму\
Dagger DI\
Presenter -> @Singleton\
Поддержка разрешений 720x1280, 1080x1920, 720x1184, 480x800, 480x854, 540x960\
Раскидать drawable по всяким drawable-hdpi

## Недостатки проекта
В проекте не использован Dagger. Решение не обосновано чем-то рациональным, просто пока нет времени на внедрение. Но если приложение оставить маленьким, Dagger ему не особенно и нужен.\
UPD. **В проекте есть примерно три метки TODO Dagger. Везде, где позже можно применить Dagger, ставлю метку**

## Мои мысли насчёт потенциальных улучшений
// TODO
Локализацию точно можно сделать лучше.\
В movie_item.xml вместо всякой магии вроде `android:minHeight="180dp"`, скорее всего, можно сделать что-то лучше.\
Возможно, стоит изменить RelativeLayout на ConstraintLayout.\
Элементы UI в XML можно было бы растащить по разным файлам.


# FAQ
#### Что нужно для запуска?
В `Constants.java` вписать свой `API_KEY`
#### Почему в приложении есть слишком очевидные недостатки?
Это мой первый проект на Android, так что могут быть ошибки новичка в специфических для Android вопросах.

# Отчёт по затраченному времени
// TODO в конце

