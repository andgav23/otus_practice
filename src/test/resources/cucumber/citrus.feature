# language: ru
#Функционал: SOAP helper


Функционал: Citrus examples

#  Структура сценария: SOAP helper
#    Если Отправить xml файл "<request>"
#    Тогда Получен ответ по схеме xsd с содержимым "<response>"
#    Примеры:
#      | request      | response             |
#      | xml/Some.xml | xml/SOAPResponse.xml |
#
#  Структура сценария: HTTP helper
#    Если Запросить данные о пользователе "<id>"
#    Тогда Ответ соответствует json схеме User
#    Если Запросить данные о пользователе "<id>"
#    Тогда Получен класс пользователя <grade>
#    Примеры:
#      | id       | grade |
#      | user-id1 | 2     |

  Структура сценария: HTTP helper
    Если Отправить сообщение "<payload>"

    Примеры:
      | payload  |
      | user-id1 |

