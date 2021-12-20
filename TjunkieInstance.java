/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package tjunkie;

import java.util.HashMap;
import java.util.Map;

class TjunkieInstance {
  private TjunkieClass klass;
 private final Map<String, Object> fields = new HashMap<>();
  TjunkieInstance(TjunkieClass klass) {
    this.klass = klass;
  }
  Object get(Token name) {
    if (fields.containsKey(name.lexeme)) {
      return fields.get(name.lexeme);
    }
    TjunkieFunction method = klass.findMethod(name.lexeme);
    if (method != null) return method.bind(this);

    throw new RuntimeError(name, 
        "Propriété non définie '" + name.lexeme + "'.");
  }
  void set(Token name, Object value) {
    fields.put(name.lexeme, value);
  }  
  @Override
  public String toString() {
    return klass.name + " instance";
  }
}