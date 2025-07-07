import { Component, inject, OnInit, OnDestroy } from '@angular/core';
import { Store } from '@ngrx/store';
import { selectCartItems } from '../../../core/store/cart/cart.selectors';
import { getCart, removeFromCart } from '../../../core/store/cart/cart.actions';
import { CommonModule } from '@angular/common';
import { CartItem } from '../../../core/interfaces/model';
import { Subscription } from 'rxjs';
import {Router} from '@angular/router';

@Component({
  selector: 'app-shopping-cart',
  imports: [CommonModule],
  templateUrl: './shopping-cart.component.html',
  styleUrl: './shopping-cart.component.scss',
  standalone: true
})
export class ShoppingCartComponent implements OnInit, OnDestroy {
  private store = inject(Store);

  products$ = this.store.select(selectCartItems);
  products: CartItem[] = [];
  prix: number = 0;
  quantity: number = 0;
  private subscription?: Subscription;
  constructor(private router: Router) {
  }

  ngOnInit(): void {
    // Load the cart once (you may want to replace '1' with dynamic cartId)
    this.store.dispatch(getCart({ cartId: 1 }));

    // Subscribe once and update products and prix reactively
    this.subscription = this.products$.subscribe(items => {
      this.products = items;

      // Recalculate total price whenever products change
      this.prix = this.products.reduce(
        (total, cartItem) => total + cartItem.product.price * cartItem.quantity,
        0
      );
    });
  }

  removeItemFromCart(productId: number) {
    this.store.dispatch(removeFromCart({ productId }));
    // No need to reload manually — NgRx store update triggers the subscription
  }

  ngOnDestroy(): void {
    this.subscription?.unsubscribe(); // Avoid memory leaks
  }

  private recalculateTotal(): void {
    this.prix = this.products.reduce(
      (total, cartItem) => total + cartItem.product.price * cartItem.quantity,
      0
    );
  }
  addQuantity(productId: number) {
    const item = this.products.find(p => p.product.id === productId);
    if (item) {
      item.quantity += 1;
      this.products = [...this.products]; // clone pour forcer Angular à redessiner
      this.recalculateTotal();
    }
  }

  minusQuantity(productId: number) {
    const item = this.products.find(p => p.product.id === productId);
    if (item && item.quantity > 1) {
      item.quantity -= 1;
      this.products = [...this.products]; // clone pour forcer Angular à redessiner
      this.recalculateTotal();
    }
  }


  gotoclientinfo() {
    this.router.navigate(['/client'])
  }
}
