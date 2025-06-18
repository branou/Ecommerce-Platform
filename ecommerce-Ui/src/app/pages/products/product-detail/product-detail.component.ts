import {Component, inject, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Store} from '@ngrx/store';
import {selectProductById} from '../../../core/store/product/product.selectors';
import {Observable} from 'rxjs';
import {Product} from '../../../core/interfaces/model';
import {AsyncPipe, CommonModule} from '@angular/common';
import {addToCart} from '../../../core/store/cart/cart.actions';

@Component({
  selector: 'app-product-detail',
  imports: [
    AsyncPipe, CommonModule
  ],
  templateUrl: './product-detail.component.html',
  styleUrl: './product-detail.component.scss',
  standalone: true
})
export class ProductDetailComponent implements OnInit {
  productId: number | null = null;
  product$!: Observable<Product | undefined>; // Optional typing
  constructor(private route: ActivatedRoute, private store: Store) {}

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    this.productId = idParam ? +idParam : null;
    if (this.productId !== null) {
      this.product$ = this.store.select(selectProductById(this.productId));
    }
  }
  quantity:number =0;
  additemToCart(product: Product) {
    this.store.dispatch(addToCart({product:product,quantity:this.quantity}))
  }
}
