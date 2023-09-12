server port = 9090
db port = 9091

psql -h localhost -p 9091 -U user -d stock_etl_tool

switch log level:
http://localhost:9092/actuator/loggers/com.scott.stock.stockdataetltool.web.controller
{"configuredLevel": "INFO"}