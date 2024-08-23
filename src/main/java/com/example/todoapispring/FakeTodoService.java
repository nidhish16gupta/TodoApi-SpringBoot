package com.example.todoapispring;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("fakeTodoService")
public class FakeTodoService implements TodoService {

    @TimeMonitor
    public String addTodo() {  // Join Point
        return  "Something";
    }


}
