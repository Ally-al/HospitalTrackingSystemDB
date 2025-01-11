# HospitalTrackingSystemDB
Приложение написано на Java.  
Для графического интерфейса использован javaFX.  
Для связи базы данных с приложением использован JDBC.  
Графический интерфейс создан в SceneBuilder.

## Задание
Необходимо автоматизировать работу приемного отделения больницы. Для этого в рамках базы данных PostgreSQL необходимо написать клиентское приложение на базе компонентов JDBC.

Под автоматизацией работы приемного отделения понимается организация
системы учета больных. В приемном отделении больницы существует дежурный врач, который устанавливает диагноз поступающим больным. Должно быть исключено помещение в одну палату больных с разными диагнозами. При распределении больных должно минимизироваться количество занимаемых больными палат. Палата характеризуется наименованием и количеством мест. Диагноз характеризуется наименованием. Больной характеризуется фамилией, именем и отчеством.

## База данных должна удовлетворять следующим требованиям:

1.	Контроль целостности данных, используя механизм связей
2.	Операции модификации групп данных и данных в связанных таблицах должны быть выполнены в рамках транзакций.
3.	Логика работы приложения должна контролироваться триггерами. В частности:
•	Триггер должен запрещать помещение больного в переполненную палату
•	Триггер должен исключать больного из палаты при смене диагноза.
4.	Все операции вычисления различных показателей (из требований к клиентскому приложению) должны реализовываться хранимыми процедурами.

## Требования к клиентскому приложению:

1.	Необходимо реализовать интерфейсы для ввода, модификации и удаления
•	Больных
•	Диагнозов
•	Палат
2.	Должен быть реализован диалог (или группа диалогов), визуализирующие процесс распределения больных по палатам. Рекомендуется совместить с диалогом ввода, модификации и удаления больного. Например, в табличной форме отображать содержимое палат.
3.	Должен быть реализован диалог анализа, отображающий диаграмму процентов загрузки по палатам.
