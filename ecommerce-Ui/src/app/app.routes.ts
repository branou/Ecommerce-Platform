import { Routes } from '@angular/router';
import {HomeComponent} from './pages/home/home.component';
import {SigninComponent} from './pages/signin/signin.component';
import {CheckoutComponent} from './pages/payment/checkout/checkout.component';
import {PaymentFailedComponent} from './pages/payment/payment-failed/payment-failed.component';
import {PaymentSucceededComponent} from './pages/payment/payment-succeeded/payment-succeeded.component';
import {AllProductsComponent} from './pages/products/all-products/all-products.component';
import {ProductDetailComponent} from './pages/products/product-detail/product-detail.component';
import {ShoppingCartComponent} from './pages/products/shopping-cart/shopping-cart.component';

export const routes: Routes = [

  { path : 'home', component: HomeComponent},
  { path : '', redirectTo: 'home', pathMatch: 'full'},
  { path : 'checkout', component: CheckoutComponent},
  { path : 'paymentFailed', component: PaymentFailedComponent},
  { path : 'paymentSucceeded', component: PaymentSucceededComponent},
  { path : 'products', component: AllProductsComponent},
  { path : 'shoppingCart', component: ShoppingCartComponent},
  { path : 'productDetail', component: ProductDetailComponent},
  { path : 'signin', component: SigninComponent},
];
