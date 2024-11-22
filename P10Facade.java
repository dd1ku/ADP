import java.util.Scanner;
public class P10Facade{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        HotelFacade hotelFacade = new HotelFacade();

        System.out.println("\nСценарий 1: Бронирование номера с услугами ресторана и уборки.");
        hotelFacade.bookRoomWithServices("Алиса", "Паста");

        System.out.println("\nСценарий 2: Организация мероприятия с номерами и оборудованием.");
        hotelFacade.organizeEvent("Техническая конференция", "Проектор");

        System.out.println("\nСценарий 3: Бронирование стола в ресторане с заказом такси.");
        hotelFacade.bookTableWithTaxi("Боб");

        System.out.println("\nСценарий 4: Отмена бронирования номера.");
        hotelFacade.cancelRoomBooking("Алиса");

        System.out.println("\nСценарий 5: Запрос на уборку.");
        hotelFacade.requestCleaning("Комната 101");

        in.close();
    }
}
class RoomBookingSystem{
    public void bookRoom(String guestName){
        System.out.println("Номер забронирован для " + guestName);
    }

    public void cancelRoomBooking(String guestName){
        System.out.println("Бронирование номера отменено для " + guestName);
    }

    public boolean isRoomAvailable(){
        System.out.println("Проверка доступности номера...");
        return true;
    }
}

class RestaurantSystem {
    public void bookTable(String guestName){
        System.out.println("Стол забронирован для " + guestName);
    }

    public void orderFood(String guestName, String food){
        System.out.println("Еда заказана для " + guestName + ": " + food);
    }

    public void cancelTableBooking(String guestName){
        System.out.println("Бронирование стола отменено для " + guestName);
    }
}

class EventManagementSystem{
    public void bookEventHall(String eventName){
        System.out.println("Конференц-зал забронирован для " + eventName);
    }

    public void orderEquipment(String eventName, String equipment){
        System.out.println("Оборудование " + equipment + " заказано для " + eventName);
    }

    public void cancelEvent(String eventName){
        System.out.println("Бронирование мероприятия отменено для " + eventName);
    }
}

class CleaningService{
    public void scheduleCleaning(String roomNumber, String time){
        System.out.println("Уборка запланирована для комнаты " + roomNumber + " на " + time);
    }

    public void performCleaning(String roomNumber){
        System.out.println("Уборка выполнена для комнаты " + roomNumber);
    }

    public void requestImmediateCleaning(String roomNumber){
        System.out.println("Запрошена немедленная уборка для комнаты " + roomNumber);
    }
}

class HotelFacade{
    private RoomBookingSystem roomBooking;
    private RestaurantSystem restaurant;
    private EventManagementSystem eventManagement;
    private CleaningService cleaningService;

    public HotelFacade(){
        roomBooking = new RoomBookingSystem();
        restaurant = new RestaurantSystem();
        eventManagement = new EventManagementSystem();
        cleaningService = new CleaningService();
    }

    public void bookRoomWithServices(String guestName, String foodOrder){
        if (roomBooking.isRoomAvailable()){
            roomBooking.bookRoom(guestName);
            restaurant.orderFood(guestName, foodOrder);
            cleaningService.scheduleCleaning("Комната для " + guestName, "18:00");
            System.out.println("Номер забронирован с услугами ресторана и уборки.");
        } else{
            System.out.println("Номер недоступен.");
        }
    }

    public void organizeEvent(String eventName, String equipment){
        eventManagement.bookEventHall(eventName);
        eventManagement.orderEquipment(eventName, equipment);
        roomBooking.bookRoom("Участники " + eventName);
        System.out.println("Мероприятие организовано с номерами и оборудованием.");
    }

    public void bookTableWithTaxi(String guestName){
        restaurant.bookTable(guestName);
        System.out.println("Такси заказано для " + guestName + " после посещения ресторана.");
    }

    public void cancelRoomBooking(String guestName){
        roomBooking.cancelRoomBooking(guestName);
    }

    public void cancelEvent(String eventName){
        eventManagement.cancelEvent(eventName);
    }

    public void cancelTableBooking(String guestName){
        restaurant.cancelTableBooking(guestName);
    }

    public void requestCleaning(String roomNumber){
        cleaningService.requestImmediateCleaning(roomNumber);
    }
}