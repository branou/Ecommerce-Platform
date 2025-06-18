import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {CartItem, Product} from '../interfaces/model';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private readonly url = 'http://localhost:8082/api/carts';
  constructor(private http: HttpClient) {}
  getCart(cartId: number){
    return this.http.get<CartItem[]>(this.url+'/items/'+cartId);
  }
  addItemToCart(product : Product,quantity:number){
    return this.http.post<Product>(this.url+`/addToCart?quantity=${quantity}`,product);
  }
  removeItemFromCart(productId:number){
    return this.http.delete(this.url+'/removeFromCart/'+productId)
  }

}
