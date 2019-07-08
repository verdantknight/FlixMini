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
Приложение загружает данные с REST API и показывает их пользователю. Сущности, которые Retrofit достаёт из REST API:
<p align="center">
<img src="https://raw.githubusercontent.com/verdantknight/FlixMini/master/img/entities.jpg" alt="" /><br />
<i>
Entities
</i>
</p>
Они соответствуют API:

```json
{
  "mPage": 1,
  "mResults": [
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
Пока так)

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
#### //TODO Подход к архитектуре: **MVP**
<p align="center">
<img width="180" src="https://raw.githubusercontent.com/verdantknight/FlixMini/master/img/MVP.jpg" alt="" /><br />
<i>
// TODO после постеров
</i>
</p>

# Работа над приложением
## TODO
###### Напишу заранее список задач, по которому делаю приложение. Некоторые функции могу не успеть сделать.
~~Просто отображение названия в списке~~\
~~Заменить List View на Recycler View~~\
~~Отображение описания в списке~~\
~~Отображение даты выхода в списке~~\
Отображение постеров в списке\
**Паттерн MVP**\
Поиск фильмов\
Поиск фильмов: pull to refresh\
Поиск фильмов: обработка ошибки\
Лайк фильму\
Поддержка разрешений 720x1280, 1080x1920, 720x1184, 480x800, 480x854, 540x960

## Недостатки проекта
В проекте не использован Dagger. Решение не обосновано чем-то рациональным, просто пока нет времени на внедрение. Но если приложение оставить маленьким, Dagger ему не особенно и нужен.

## Мои мысли насчёт потенциальных улучшений
// TODO


# FAQ
#### Что нужно для запуска?
В Constants.java вписать API_KEY
#### Почему в приложении есть слишком очевидные недостатки?
Это мой первый проект на Android, так что могут быть ошибки новичка в специфических для Android вопросах.

# Отчёт по затраченному времени
// TODO в конце

