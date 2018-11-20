1.select IdProduct,DescripcionP,SellPrice 
from product 
where SellPrice>=10000.00

2.select product.IdProvider,NombreP,product.* 
from provider,product 
where provider.Idprovider=product.idProvider 
and NombreP='NVIDIA'

3.select idfactura,client.idclient,nombrec,fecha,subtotal,saldoc 
from client,factura 
where factura.idclient=client.idclient 
and nombrec='Duck';

4.select * 
from factura 
where fecha
between '01/08/2017 00:00:00.00' 
and '31/08/2017 11:59:59.59';

5.select client.* 
from factura,client 
where factura.idclient=client.idclient 
and fecha
between '01/08/2018 00:00:00.00' 
and current_timestamp;

6.select client.* 
from client,factura,detalle,provider,product 
where factura.idclient=client.idclient 
and detalle.idfactura=factura.idfactura 
and product.idproduct=detalle.idproduct 
and product.idprovider=provider.idprovider 
and nombrep='NINTENDO';

7.Select product.* 
from product,detalle,factura 
where detalle.idproduct=product.idproduct 
and factura.idfactura=detalle.idfactura 
and fecha 
not between current_timestamp and current_timestamp + interval '-12 month';
(no toma en cuenta los productos que nunca se han vendido)

11.select * 
from product 
where stocked<restock;
