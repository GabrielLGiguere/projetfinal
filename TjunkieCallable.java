/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package tjunkie;

import java.util.List;

interface TjunkieCallable {
  int arity();
  Object call(Interpreter interpreter, List<Object> arguments);
}