﻿# FlixMini
Небольшое Android-приложение для загрузки и отображения информации о фильмах с TMDB.

# Процесс создания
Первым делом - составить список, какие функции нужны в итоговом приложении. Что я понял из документации:
<p align="center">
<img src="https://raw.githubusercontent.com/verdantknight/FlixMini/master/img/usecase.jpg" alt="" /><br />
<font size="12">Use case diagram</font>
</p>
Для начала, я сделал простой список (List View) с отображением загруженных названий.
<p align="center">
<img width="180" src="https://raw.githubusercontent.com/verdantknight/FlixMini/master/img/interfacebeta.jpg" alt="" /><br />
<font size="12">Первый вариант интерфейса</font>
</p>
Это позволило освоиться с созданием проекта в Android и примерно понять, от чего отталкиваться.\
Приложение загружает данные с REST API и показывает их пользователю. Сущности, которые Retrofit достаёт из REST API:
<p align="center">
<img src="https://raw.githubusercontent.com/verdantknight/FlixMini/master/img/entities.jpg" alt="" /><br />
<font size="12">Entities</font>
</p>
Они соответствуют API:
```
{
  "page": 1,
  "results": [
  ...array of movies...
  ]
}
```
Всё приложение - пользовательский интерфейс и написанный в лучших традициях плохого кода вросший в него обработчик от Retrofit:
<p align="center">
<img src="https://raw.githubusercontent.com/verdantknight/FlixMini/master/img/classes.jpg" alt="" /><br />
<font size="12">Схема основных классов приложения</font>
</p>


# Технические характеристики
#### Поддержка версий: Android 5.0+
Обоснование: требования в задании
#### Поддерживаемые языки: **русский**
Обоснование: требования в задании
#### Язык реализации: **Java**
Обоснование: уже знаю Java, Kotlin'ом (почти) не занимался, так что для быстроты реализации лучше Java.
#### //TODO Подход к архитектуре: **MVP**

# TODO
###### Напишу заранее список задач, по которому делаю приложение. Некоторые функции могу не успеть сделать.
~~Просто отображение названия в списке~~\
Заменить List View на Recycler View\
Отображение описания в списке\
Отображение даты выхода в списке\
Отображение постеров в списке\
Паттерн MVP\
Поиск фильмов\
Поиск фильмов: pull to refresh\
Поиск фильмов: обработка ошибки\
Лайк фильму\
Поддержка разрешений 720x1280, 1080x1920, 720x1184, 480x800, 480x854, 540x960

# Недостатки проекта
В проекте не использован Dagger. Решение не обосновано чем-то рациональным, просто пока нет времени на внедрение.

# FAQ
**Как запускать?**\
В Constants.java вписать API_KEY

# Отчёт по затраченному времени
