import { ApplicationConfig, provideZoneChangeDetection, isDevMode } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import {provideHttpClient} from '@angular/common/http';
import {provideState, provideStore} from '@ngrx/store';
import { provideEffects } from '@ngrx/effects';
import { provideStoreDevtools } from '@ngrx/store-devtools';
import {productReducer} from './core/store/product/product.reducer';
import {ProductEffects} from './core/store/product/product.effect';

export const appConfig: ApplicationConfig = {
  providers: [provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideHttpClient(), provideStore({products:productReducer}), provideEffects(ProductEffects), provideStoreDevtools({ maxAge: 25, logOnly: !isDevMode() })]
};
