https://miro.com/app/board/uXjVNQy_pxY=/


* createOrder активирует createOrderSaga:
  1. добавляет ордер в аутбокс на оплату https://medium.com/@egorponomarev/outbox-pattern-in-spring-boot-8e8cf116f044 (записывает в базу PENDING)
  2. резервирует инвентарь (с проверкой если он есть APPROVED) только если статус PENDING
  3. проводит оплату (PAID) только если статус APPROVED

* track order получает статус
* cancel order

1. если статус PENDING то сразу ставим CANCELED
2. если APPROVED то cancelRestarauntReservation статус CANCELLING
3. если PAID то cancelPaidOrder статус CANCELLING

cancelRestarauntReservationSaga

1. посылает запрос на отмену резервации
2. статус CANCELED
3. отсылаем событие restarauntReservationCanceledEvent

cancelPaidOrderSaga

1. посылаем запрос в payment
2. если ок то ставим статус CANCELED
3. paidOrderCanceledEvent