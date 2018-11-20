Insert into Category values (1,'Almacenamiento');
Insert into Category values (2,'Entrada');
Insert into Category values (3,'Audiovisual');
Insert into Category values (4,'Componentes internos');
Insert into Category values (5,'Software');
Insert into category values (7,'Consola');
Insert into category values (8,'Celular');
insert into category values (9,'Otros');

Copy (Query) to 'Direccion del archivo' delimiters '-' CSV HEADER; 

Copy detalle 
from 'C:\Users\User\Documents\DATABASES\Proyecto 2018B grupo2\test.csv' delimiters ';' 
with csv header;