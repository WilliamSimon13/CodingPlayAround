

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class demo123 extends ViewModel {
    private MutableLiveData<Integer> number;

    public LiveData<Integer> getNumber(){
        if(number == null){
            number.setValue(0);
        }

        return number;
    }

    public void increaseNumber(){
        number.setValue(number.getValue()+1);
    }
}
