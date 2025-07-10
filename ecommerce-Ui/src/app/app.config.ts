import {
  ApplicationConfig,
  provideZoneChangeDetection,
  isDevMode
} from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import {provideHttpClient, withInterceptors} from '@angular/common/http';
import { provideStore} from '@ngrx/store';
import { provideEffects } from '@ngrx/effects';
import { provideStoreDevtools } from '@ngrx/store-devtools';
import {productReducer} from './core/store/product/product.reducer';
import {ProductEffects} from './core/store/product/product.effect';
import {cartReducer} from './core/store/cart/cart.reducer';
import {CartEffects} from './core/store/cart/cart.effect';
import { includeBearerTokenInterceptor} from 'keycloak-angular';
import {provideKeycloakAngular} from './keycloak.config';

export const appConfig: ApplicationConfig = {
  providers: [
    provideHttpClient(withInterceptors([includeBearerTokenInterceptor])),
    provideKeycloakAngular(),
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideHttpClient(), provideStore({products:productReducer,carts:cartReducer}), provideEffects(ProductEffects,CartEffects),
    provideStoreDevtools({ maxAge: 25, logOnly: !isDevMode() }),
  ]
};
