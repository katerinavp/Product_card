# Product_card
Карточка продукта
---
### Задачи:  

Получение информации с сервера о товарах в виде json:  https://raw.githubusercontent.com/katerinavp/JSON_LMv3/master/product.json  

•	stocks - остатки товара в регионе,  
•	34 - номер региона,  
•	2, 3, 4, ... 176 - номера магазинов региона,  
•	"2": "35" означает, что в магазине 2 товар в наличии в количестве 356 штук. 

### Результат:  

В приложение извлечена информация о товарах:  
1.	получено название товара,  
2.	получен массив номеров магазинов, в которых есть товары в наличии,  
3.	найдено максимальное количество товара в регионе, извлечено это количество и номер магазина.  

### Используемые инструменты и технологии:  

• Работа с сетью с помощью библиотеки OkHttp,    
• Сохранение состояние при повороте экрана с помощью ViewModel,  
• Десериализация данных  с помощью библиотеки Gson.  

<img src="https://github.com/katerinavp/Product_card/blob/master/Screenshot_empty.jpg" width="200" height="400"> <img src="https://github.com/katerinavp/Product_card/blob/master/Screenshot_infoProduct.jpg" width="200" height="400">  
<img src="https://github.com/katerinavp/Product_card/blob/master/Screenshot_changeOrientation.jpg" width="400" height="200">
