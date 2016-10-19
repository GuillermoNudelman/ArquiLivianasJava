package uy.edu.ort.service;

public class BusinessServiceImpl implements BusinessService {

    @Override
    public void serviceMethod(String arg) {
        System.out.println("service " + arg);
    }

    @Override
    public String serviceMethod(int number) {
        System.out.println("serviceMethod number ");
        String str = "=> {" + number+"}";
        return str;
    }
}
