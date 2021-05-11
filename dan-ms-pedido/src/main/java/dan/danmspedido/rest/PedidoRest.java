package dan.danmspedido.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dan.danmspedido.Pedido;



@RestController
@RequestMapping("/api/pedido")

public class PedidoRest {
	
	private static final List<Pedido> listaPedidos=new ArrayList<>();
	private static Integer ID_GEN=1;
	
	
	 @PostMapping //punto 2) a.i.
	 public ResponseEntity<Pedido> crear (@RequestBody Pedido nuevo){
		 nuevo.setId(ID_GEN++);
		 listaPedidos.add(nuevo);
		 return ResponseEntity.ok(nuevo);
	 }
	 
	 @PutMapping(path = "/{id}") // 2) punto b
	 public ResponseEntity<Pedido> actualizar(@RequestBody Pedido nuevo,  @PathVariable Integer id){
	        OptionalInt indexOpt =   IntStream.range(0, listaPedidos.size())
	        .filter(i -> listaPedidos.get(i).getId().equals(id))
	        .findFirst();

	        if(indexOpt.isPresent()){
	            listaPedidos.set(indexOpt.getAsInt(), nuevo);
	            return ResponseEntity.ok(nuevo);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	 
	 
	    @DeleteMapping(path = "/{id}") //2) punto c.i.
	    public ResponseEntity<Pedido> borrar(@PathVariable Integer id){
	        OptionalInt indexOpt =   IntStream.range(0, listaPedidos.size())
	        .filter(i -> listaPedidos.get(i).getId().equals(id))
	        .findFirst();

	        if(indexOpt.isPresent()){
	            listaPedidos.remove(indexOpt.getAsInt());
	            return ResponseEntity.ok().build();
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	    
		 @GetMapping(path = "/{id}") // 2) punto d. i.
			public ResponseEntity<Pedido> pedidoPorId(@PathVariable Integer id){

			      Optional<Pedido> c =  listaPedidos
			              .stream()
			              .filter(unCli -> unCli.getId().equals(id))
			              .findFirst();
			      return ResponseEntity.of(c);
			 }
}
