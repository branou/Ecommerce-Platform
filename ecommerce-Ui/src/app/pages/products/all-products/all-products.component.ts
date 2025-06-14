import {Component, inject, OnInit} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';
import {Store} from '@ngrx/store';
import {getAllProducts} from '../../../core/store/product/product.actions';
import {selectAllProducts} from '../../../core/store/product/product.selectors';
import {Router} from '@angular/router';

@Component({
  selector: 'app-all-products',
  imports: [FormsModule, CommonModule],
  templateUrl: './all-products.component.html',
  styleUrl: './all-products.component.scss',
  standalone: true
})
export class AllProductsComponent implements OnInit{
  price: number = 500;
  min: number = 0;
  max: number = 1000;
  constructor(private router: Router) {
  }

  gotoproductdetails(productId: number, event: Event) {
    event.preventDefault();
    console.log('Go to details for product:', productId);
    // Your navigation or logic here
    this.router.navigate(['/products', productId]);
  }

  get formattedPrice(): string {
    return `${this.price} DHS`;
  }

  private store = inject(Store);
  products$ = this.store.select(selectAllProducts);

  ngOnInit(): void {
    this.loadProducts()
  }
  page: number = 0;
  size: number = 10;
  loadProducts() {
    this.store.dispatch(getAllProducts({ page: this.page, size: this.size }));
  }
  /*products : Product[] = [
    {
      id: 1,
      name: 'EVAWIN ROSAWIN® GEL NETTOYANT MDZ',
      description: '200 ml',
      price: 149,
      stock: true,
      imageUrl: 'https://parapharma.ma/34506-medium_default/evawin-rosawin-gel-nettoyant-mdz-200ml.webp',
    },
    {
      id: 2,
      name: 'SVR AMPOULE B3 REPULPANT ET ANTI-RIDES',
      description: '30 ml',
      price: 259,
      stock: true,
      imageUrl: 'https://parapharma.ma/31421-medium_default/svr-ampoule-b-30ml.webp',
    },
    {
      id: 3,
      name: 'SVR SPIRIAL SPRAY ANTI- TRANSPIRANT',
      description: '75 ml',
      price: 95.0,
      stock: false,
      imageUrl: 'https://parapharma.ma/31416-medium_default/svr-spirial-spray-anti-transpirant-75ml.webp',
    },
    {
      id: 4,
      name: 'Beliflor SHAMPOOING ANTIPELLICULAIRE',
      description: '250 ml',
      price: 90,
      stock: true,
      imageUrl: 'https://parapharma.ma/7663-medium_default/beliflor-shampooing-antipelliculaire-250ml.webp',
    },
    {
      id: 5,
      name: 'VICHY DEODORANT MINERAL MGO',
      description: '125 ml',
      price: 119,
      stock: true,
      imageUrl: 'https://parapharma.ma/48295-medium_default/vichy-deodorant-mineral-mgo-125ml-.webp',
    },
    {
      id: 6,
      name: 'SVR SUN SECURE LAIT BIODEGRADABLE SPF 50+',
      description: '250 ml',
      price: 289,
      stock: true,
      imageUrl: 'https://parapharma.ma/36258-medium_default/svr-sun-secure-lait-biodegradable-spf-50-200ml.webp',
    }
  ];*/
}
