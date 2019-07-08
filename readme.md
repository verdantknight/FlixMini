# FlixMini
Небольшое Android-приложение для загрузки и отображения информации о фильмах с TMDB, **пока - просто заголовков фильмов.**\
Но не думаю, что поправить XML-список и добавить туда картинок будет проблемой.
<p align="center">
<img width="180" src="https://raw.githubusercontent.com/verdantknight/FlixMini/master/img/interfacebeta.jpg" alt="" /><br />
<font size="12">Интерфейс приложения сейчас</font>
</p>

## Технические характеристики
Android 5.0+\
Поддерживаемые языки: **русский**\
Язык реализации: **Java**


## Описание работы
Приложение загружает данные с REST API и показывает их пользователю. В идеале, пользователь сможет ещё и искать фильмы.
<p align="center">
<img src="https://raw.githubusercontent.com/verdantknight/FlixMini/master/img/usecase.jpg" alt="" /><br />
<font size="12">Use case diagram</font>
</p>
Сущности, которые Retrofit достаёт из REST API:
<p align="center">
<img src="https://raw.githubusercontent.com/verdantknight/FlixMini/master/img/entities.jpg" alt="" /><br />
<font size="12">DTO приложения</font>
</p>
Они соответствуют API:
`
{
  "page": 1,
  "results": [
  ...
  ]
}
`\
Всё приложение - пользовательский интерфейс и написанный в лучших традициях плохого кода вросший в него обработчик от Retrofit:
<p align="center">
<img src="https://raw.githubusercontent.com/verdantknight/FlixMini/master/img/classes.jpg" alt="" /><br />
<font size="12">Схема основных классов приложения</font>
</p>

## TODO
~~Отображение названия в списке~~\
Отображение описания в списке\
Отображение даты выхода в списке\
Отображение постеров в списке\
Поиск фильмов\
Лайк фильму

## FAQ
**Как запускать?**\
В Constants.java вписать API_KEY
