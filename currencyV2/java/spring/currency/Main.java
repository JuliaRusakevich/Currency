package spring.currency;


public class Main {

    public static void main(String[] args) {

    }
}


//132
//136	KYD	Доллар островов Кайман
     /*
        CreateCurrencyDto dto = new CreateCurrencyDto();

        dto.setTitle("116");
        dto.setDescription("Риель");
        dto.setCode("KHR");


        CreateMapper mapper = CreateMapper.getInstance();

        var entity = mapper.mapFrom(dto);


        try {
            service.save(dto);
            System.out.println(dto);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
  */

//

/*
Читает по id!!!
        List<Currency> list = null;
        try {
            list = dao.read();
        } catch (DAOException e) {
            System.out.println(e.getMessage());
        }
        list.forEach(System.out::println);



 Создает:
        Currency entity= new Currency();
        entity.setTitle("152");
        entity.setDescription("Чилийское песо");
        entity.setCode("CLP");
        try {
            dao.create(entity);
        } catch (DAOException e) {
            System.out.println(e.getMessage());
        }
Обновляет:
        Currency old= new Currency();
        old.setId(3);
        old.setTitle("068 update");
        old.setDescription("Боливиано update");
        old.setCode("BOB");

        Currency entity= new Currency();

        entity.setTitle("132");
        entity.setDescription("Эскудо Кабо-Верде");
        entity.setCode("CVE");
        try {
            dao.update(old, entity);
            System.out.println("OLD:" + old);
            System.out.println();
            System.out.println("NEW:" + entity);;
        } catch (DAOException e) {
            System.out.println(e.getMessage());
        }
    }

Читает всех:
        try {
            dao.read().forEach(System.out::println);

        } catch (DAOException e) {
            System.out.println(e.getMessage());
        }

 */
