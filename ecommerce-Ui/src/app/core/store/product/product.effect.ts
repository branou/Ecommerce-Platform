import { inject, Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { Observable, of } from 'rxjs';
import { exhaustMap, map, catchError } from 'rxjs/operators';
import { Action } from '@ngrx/store';
import { ProductService } from '../../services/product.service';
import * as ProductActions from './product.actions';

@Injectable()
export class ProductEffects {
  private actions$ = inject(Actions);
  private productService = inject(ProductService);

  loadProducts$ = createEffect(() =>
    this.actions$.pipe(
      ofType(ProductActions.getAllProducts),
      exhaustMap(action =>
        this.productService.getProducts(action.page, action.size).pipe(
          map(response => ProductActions.getAllProductsSuccess({ response })),
          catchError(error => of(ProductActions.getAllProductsFailure({ error })))
        )
      )
    )
  );

}
