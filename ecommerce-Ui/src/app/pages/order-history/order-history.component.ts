import { Component } from '@angular/core';
import {CommonModule} from '@angular/common';
import {Order} from '../../core/interfaces/Order';

@Component({
  selector: 'app-order-history',
  imports: [
    CommonModule
  ],
  templateUrl: './order-history.component.html',
  styleUrl: './order-history.component.scss',
  standalone: true
})
export class OrderHistoryComponent {
  orders: Order[] = [
    {
      id: '#FWB127364372',
      date: '20.12.2023',
      price: 4756,
      status: 'Pre-order',
    },
    {
      id: '#FWB125467980',
      date: '11.12.2023',
      price: 499,
      status: 'In transit',
    },
    {
      id: '#FWB139485607',
      date: '08.12.2023',
      price: 85,
      status: 'Confirmed',
    },
    {
      id: '#FWB144d485607',
      date: '08.11.2023',
      price: 45,
      status: 'Cancelled',
    }
  ];

  getStatusLabel(status: string): string {
    switch (status) {
      case 'Pre-order':
        return 'Précommande';
      case 'In transit':
        return 'En transit';
      case 'Confirmed':
        return 'Confirmée';
      case 'Cancelled':
        return 'Annulée';
      default:
        return status;
    }
  }

  getStatusClasses(status: string): string {
    switch (status) {
      case 'Pre-order':
        return 'bg-primary-100 text-primary-800 dark:bg-primary-900 dark:text-primary-300';
      case 'In transit':
        return 'bg-yellow-100 text-yellow-800 dark:bg-yellow-900 dark:text-yellow-300';
      case 'Confirmed':
        return 'bg-green-100 text-green-800 dark:bg-green-900 dark:text-green-300';
      case 'Cancelled':
        return 'bg-red-100 text-red-800 dark:bg-red-900 dark:text-red-300';
      default:
        return '';
    }
  }

  getStatusIconPath(status: string): string {
    switch (status) {
      case 'Confirmed':
        return 'M5 11.917 9.724 16.5 19 7.5'; // example checkmark icon
      case 'Cancelled':
        return 'M6 18 17.94 6M18 18 6.06 6'; // example X icon
      case 'In transit':
        return 'M13 7h6l2 4m-8-4v8m0-8V6a1 1 0 0 0-1-1H4a1 1 0 0 0-1 1v9h2m8 0H9m4 0h2m4 0h2v-4m0 0h-5m3.5 5.5a2.5 2.5 0 1 1-5 0 2.5 2.5 0 0 1 5 0Zm-10 0a2.5 2.5 0 1 1-5 0 2.5 2.5 0 0 1 5 0Z'; // example box
      default:
        return 'M18.5 4h-13m13 16h-13M8 20v-3.333a2 2 0 0 1 .4-1.2L10 12.6a1 1 0 0 0 0-1.2L8.4 8.533a2 2 0 0 1-.4-1.2V4h8v3.333a2 2 0 0 1-.4 1.2L13.957 11.4a1 1 0 0 0 0 1.2l1.643 2.867a2 2 0 0 1 .4 1.2V20H8Z'; // example plus icon
    }
  }

}
