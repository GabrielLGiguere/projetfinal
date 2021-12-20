/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package tjunkie;

import java.util.List;

class TjunkieFunction implements TjunkieCallable {
  private final Stmt.Function declaration;
  private final Environment closure;
  private final boolean isInitializer;
  TjunkieFunction(Stmt.Function declaration, Environment closure,
              boolean isInitializer) {
    this.isInitializer = isInitializer;
    this.closure = closure;
    this.declaration = declaration;
  }
  TjunkieFunction bind(TjunkieInstance instance) {
    Environment environment = new Environment(closure);
    environment.define("ceci", instance);
    return new TjunkieFunction(declaration, environment,
                           isInitializer);
  }  
  @Override
  public String toString() {
    return "<fn " + declaration.name.lexeme + ">";
  }
  @Override
  public int arity() {
    return declaration.params.size();
  }
  @Override
  public Object call(Interpreter interpreter,
                     List<Object> arguments) {
    Environment environment = new Environment(closure);
    for (int i = 0; i < declaration.params.size(); i++) {
      environment.define(declaration.params.get(i).lexeme,
          arguments.get(i));
    }

    try {
      interpreter.executeBlock(declaration.body, environment);
    } catch (Return returnValue) {
      if (isInitializer) return closure.getAt(0, "ceci");        
      return returnValue.value;
    }
    if (isInitializer) return closure.getAt(0, "ceci");    
    return null;
  }
}