# language: ru
#Функционал: SOAP helper


Функционал: Citrus examples

  Структура сценария: SOAP helper
    Если Отправить xml файл "<request>"
    Тогда Получен ответ по схеме xsd с содержимым "<response>"
    Примеры:
      | request      | response             |
      | xml/Some.xml | xml/SOAPResponse.xml |

  Структура сценария: HTTP helper
    Если Запросить данные о пользователе "<id>"
    Тогда Ответ соответствует json схеме User
    Если Запросить данные о пользователе "<id>"
    Тогда Получен класс пользователя <grade>
    Примеры:
      | id       | grade |
      | user-id1 | 2     |

  Структура сценария: MQ helper
    Если Отправить сообщение "<message>"
    Тогда Количество сообщений в очереди <message_count>
    Тогда Сообщение в очереди совпадает с "<message>"
    Примеры:
      | message                    | message_count |
      | __files/json/user-id1.json | 1             |

