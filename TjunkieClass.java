/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package tjunkie;

import java.util.List;
import java.util.Map;

class TjunkieClass implements TjunkieCallable {
  final String name;
  final TjunkieClass superclass;  
  private final Map<String, TjunkieFunction> methods;

  TjunkieClass(String name, TjunkieClass superclass,
           Map<String, TjunkieFunction> methods) {
    this.superclass = superclass;
    this.name = name;
    this.methods = methods;
  }
  TjunkieFunction findMethod(String name) {
    if (methods.containsKey(name)) {
      return methods.get(name);
    }
    if (superclass != null) {
      return superclass.findMethod(name);
    }
    return null;
  }  
  @Override
  public String toString() {
    return name;
  }
  @Override
  public Object call(Interpreter interpreter,
                     List<Object> arguments) {
    TjunkieInstance instance = new TjunkieInstance(this);
    TjunkieFunction initializer = findMethod("init");
    if (initializer != null) {
      initializer.bind(instance).call(interpreter, arguments);
    }    
    return instance;
  }

  @Override
  public int arity() {
    TjunkieFunction initializer = findMethod("init");
    if (initializer == null) return 0;
    return initializer.arity();
  }
}