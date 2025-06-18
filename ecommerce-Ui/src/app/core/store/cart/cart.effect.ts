import {Actions, createEffect, ofType} from '@ngrx/effects';
import {inject, Injectable} from '@angular/core';
import {CartService} from '../../services/cart.service';
import {catchError, exhaustMap, map} from 'rxjs/operators';
import {of} from 'rxjs';
import {
  addToCart,
  addToCartFailure,
  addToCartSuccess,
  getCart,
  getCartFailure,
  getCartSuccess, removeFromCart, removeFromCartFailure,
  removeFromCartSuccess
} from './cart.actions';

@Injectable()
export class CartEffects {
  private actions$ = inject(Actions);
  private cartService = inject(CartService);

  loadProducts$ = createEffect(() =>
    this.actions$.pipe(
      ofType(getCart),
      exhaustMap(  action =>
        this.cartService.getCart(action.cartId).pipe(
          map(cart => getCartSuccess({ cart })),
          catchError(error => of(getCartFailure({ error })))
        )
      )
    )
  );
  addToCart$ = createEffect(() =>
    this.actions$.pipe(
      ofType(addToCart),
      exhaustMap(action =>
        this.cartService.addItemToCart(action.product, action.quantity).pipe(
          map(() => addToCartSuccess()),
          catchError(error => of(addToCartFailure({ error })))
        )
      )
    )
  );

  removeFromCart$ = createEffect(() =>
    this.actions$.pipe(
      ofType(removeFromCart),
      exhaustMap(action =>
        this.cartService.removeItemFromCart(action.productId).pipe(
          map(() => removeFromCartSuccess({ productId: action.productId })),
          catchError(error => of(removeFromCartFailure({ error: error.message })))
        )
      )
    )
  );




}
