import { Routes } from '@angular/router';
import {HomeComponent} from './pages/home/home.component';
import {SigninComponent} from './pages/signin/signin.component';
import {CheckoutComponent} from './pages/payment/checkout/checkout.component';
import {PaymentFailedComponent} from './pages/payment/payment-failed/payment-failed.component';
import {AllProductsComponent} from './pages/products/all-products/all-products.component';
import {ProductDetailComponent} from './pages/products/product-detail/product-detail.component';
import {ShoppingCartComponent} from './pages/products/shopping-cart/shopping-cart.component';
import {WishlistComponent} from './pages/wishlist/wishlist.component';
import {OrderHistoryComponent} from './pages/order-history/order-history.component';
import {SignupComponent} from './pages/signup/signup.component';
import {ContactUsComponent} from './pages/contact-us/contact-us.component';
import {DefaultLayoutComponent} from './layouts/default-layout/default-layout.component';
import {FullLayoutComponent} from './layouts/full-layout/full-layout.component';
import {ClientPlaceComponent} from './pages/client-place/client-place.component';

export const routes: Routes = [

  {
    path: '',
    component: DefaultLayoutComponent,
    children: [
      { path: '', component: HomeComponent },
      { path: 'products', component: AllProductsComponent },
      { path : 'acceuil', component: HomeComponent},
      { path : 'checkout', component: CheckoutComponent},
      { path : 'paymentFailed', component: PaymentFailedComponent},
      { path : 'shoppingCart', component: ShoppingCartComponent},
      { path : 'products/:id', component: ProductDetailComponent},
      { path : 'wishlist', component: WishlistComponent},
      { path : 'order-history', component: OrderHistoryComponent},
      { path : 'client', component: ClientPlaceComponent}
    ]
  },
  {
    path: '',
    component: FullLayoutComponent,
    children: [
      { path: 'login', component: SigninComponent },
      { path: 'register', component: SignupComponent },
      { path : 'contactus', component: ContactUsComponent}
    ]
  }
];
