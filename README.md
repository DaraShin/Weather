# Weather
Приложение позволяет выполнять следующие запросы:  
## GET &nbsp; &nbsp; /current-weather &nbsp; &nbsp; получение сведений о текущей погоде  
**Пример ответа:**  
{  
&nbsp; "temperature_c":0.0,  
&nbsp; "wind_mph":0.0,  
&nbsp; "pressure_mb":0.0  
&nbsp; "humidity":0.0,  
&nbsp; "condition":"string",  
&nbsp; "location":  
&nbsp; {  
&nbsp; &nbsp; "latitude":0.0,  
&nbsp; &nbsp; "longitude":0.0  
&nbsp; }  
}  

## GET &nbsp; &nbsp; /average-weather &nbsp; &nbsp; получение среднесуточной температуры 
**Параметры строки запроса:**  
1. from - дата начала периода в формате "dd-mm-yyyy"  
Тип данных: String  
Обязательный параметр.  
2. to - дата окончания периода в формате "dd-mm-yyyy"  
Тип данных: String  
Обязательный параметр.  
**Пример ответа:**  
{  
&nbsp; "location":  
&nbsp; {  
&nbsp; &nbsp; "latitude":0.0,  
&nbsp; &nbsp; "longitude":0.0  
&nbsp; },  
&nbsp; "average_temperature_c":0.0,  
&nbsp; "average_wind_mph":0.0,  
&nbsp; "average_pressure_mb":0.0,  
&nbsp; "average_humidity":0.0  
}
