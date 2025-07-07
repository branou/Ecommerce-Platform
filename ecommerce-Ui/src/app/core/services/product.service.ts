import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiResponse, Product} from '../interfaces/model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private readonly url = 'http://localhost:8082/api/products';
  constructor(private http : HttpClient) { }
  getProducts(page: number = 0, size: number = 10) {
    return this.http.get<ApiResponse<Product>>(this.url+`/getAllProducts?page=${page}&size=${size}`);
  }
  getProductById(id: number) {
    return this.http.get<Product>(this.url+`/${id}`);
  }
  addProduct(product: Product) {
    return this.http.post<Product>(this.url+'/addProduct', product);
  }
  updateProduct(product: Product) {
    return this.http.put<Product>(this.url+`/updateProduct/${product.id}`, product);
  }
  deleteProduct(id: number) {
    return this.http.delete(this.url+`/deleteProduct/${id}`);
  }
  searchProduct(searchTerm: string,page: number = 0, size: number = 10){
    return this.http.get<ApiResponse<Product>>(this.url+`/getAllProductsBySearch/${searchTerm}?page=${page}&size=${size}`);
  }

}
