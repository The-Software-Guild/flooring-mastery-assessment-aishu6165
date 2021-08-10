package com.flooring.controller;

import com.flooring.model.FlooringModel;
import com.flooring.service.FlooringService;
import com.flooring.ui.UserIO;
//import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FlooringController {
   // @Autowired
    private FlooringService service;
   // @Autowired
    private UserIO userio;

    public FlooringController(FlooringService service, UserIO userio) {
        this.service = service;
        this.userio = userio;
    }

    public void run(){
        service.addProduct(new FlooringModel());
        int option = userio.showOptions();
        switch (option){
            case 1:
               String date = userio.askProductDate();
               List<FlooringModel> list = service.displayOrders(date);
               if(list!=null) {
                   userio.displayOrders(list, date);
                   this.run();
               }
               else{
                   System.out.println("Order is not Available with given Date "+date);
                   this.run();
               }
               break;
            case 2:
                List<FlooringModel> prodList = service.loadProductType();
                FlooringModel model = userio.getOrderDetails(prodList);
                FlooringModel updatedModel = service.validateOrder(model);
                if(updatedModel==null)
                    this.run();
                String str = userio.getPermission(updatedModel);
                if(str.equalsIgnoreCase("Y")){
                    System.out.println(service.saveOrder(updatedModel));
                }
                else{
                    this.run();
                }
                break;
            case 3:
               List<String> strList = userio.getEditOrderDetails();
               FlooringModel editableModel = service.getEditOrderDetails(strList);
               if(editableModel!=null){
                   List<FlooringModel> productList = service.loadProductType();
                   FlooringModel editedModel = userio.getEditableField(editableModel,productList);
                   if(editedModel.getArea().compareTo(editableModel.getArea())!=0 || !editableModel.getProductType().equals(editedModel.getProductType())
                           || !editedModel.getState().equals(editableModel.getState())){
                       FlooringModel calculatedModel = service.validateOrder(editedModel);
                       if(calculatedModel==null)
                           this.run();
                       String permission = userio.getPermission(calculatedModel);
                       if(permission.equalsIgnoreCase("Y")){
                           System.out.println(service.saveOrder(calculatedModel));
                           this.run();
                       }
                       else{
                           this.run();
                       }
                   }
                   else{
                       System.out.println(" "+service.saveOrder(editedModel));
                      this.run();

                   }
               }else{
                   System.out.println("Order is not available with given data");
                   this.run();
               }
               break;
            case 4:
                List<String> stringList = userio.getRemoveOrderDetails();
                FlooringModel removeModel = service.getEditOrderDetails(stringList);
                if(removeModel!=null) {

                    String message = userio.confirmation(removeModel);
                    if (message.equalsIgnoreCase("Y")) {
                        String removedMessage = service.removeOrder(removeModel);
                        System.out.println(removedMessage);
                    } else {
                        System.out.println("Thank You");
                    }
                }else{
                    System.out.println("Order is not Available ");
                    this.run();
                }
                break;
            case 5:
                System.out.println("Coming soon............");
                this.run();
                break;
            case 6:
                System.out.println("Thank you Visit again :");
                break;
            default:
                System.out.println("Please Enter valid options :");
                this.run();
                break;

        }

    }
}
