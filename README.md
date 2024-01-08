https://miro.com/app/board/uXjVNQy_pxY=/


* createOrder активирует createOrderSaga:
  1. добавляет ордер в аутбокс на оплату https://medium.com/@egorponomarev/outbox-pattern-in-spring-boot-8e8cf116f044 (записывает в базу PENDING)
  2. резервирует инвентарь (inventory ставит статус APPROVED, с проверкой если он есть APPROVED) только если статус PENDING
  3. проводит оплату (PAID) только если статус APPROVED

* track order получает статус
* cancel order
  1. если статус PENDING то сразу ставим CANCELED
  2. если APPROVED то cancelRestaurantReservationSaga, ставит статус CANCELLING
    при получении restaurantReservationCanceledEvent ставим статус CANCELED
  3. если PAID то cancelPaidOrder статус CANCELLING
    при получении paidOrderCanceledEvent ставим статус CANCELED