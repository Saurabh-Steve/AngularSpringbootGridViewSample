import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { Page } from '../../models/Page'
import { Product } from '../../models/Product';
import { ApiService } from '../../service/ApiService';
import { catchError, of } from 'rxjs';
import { ProductDialogComponent } from '../product-dialog/product-dialog.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-product-listing',
  templateUrl: './product-listing.component.html',
  styleUrls: ['./product-listing.component.css']
})
export class ProductListingComponent implements OnInit {
 
  displayedColumns: string[] = ['id', 'name', 'description', 'rating', 'price', 'image', 'edit'];
  products: Product[] = [];
  page?: Page<Product>;
  pageSize = 50;
  pageSizeOptions: number[] = [5, 10];
  currentPage = 0;
  searchQuery = '';
  sortType = 'ID';
  order = 'ASC';


  constructor(private apiService: ApiService
    , public dialog: MatDialog) {}

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(n? : string, sortType?: string, direction?: string, currentPage?: number) {
    console.log("sortType "+sortType)
    const type = (sortType == undefined) ? 'ID' : sortType;
    const orderType = (direction == undefined) ? 'ASC' : direction;
    this.apiService.getProducts({ name: n,
       page: currentPage ? currentPage : this.currentPage,
        size: this.pageSize, 
        sortType: type,
         order: orderType})
    .pipe(catchError((error: HttpErrorResponse) => {
        console.log(error.message);
        return of(null); 
      })
    )
    .subscribe((data: Page<Product> | null) => {
      if (data) { 
        this.page = data;
        this.products = data.content;
      } else {
        this.products = [];
      }
    });
  }

   onClickSearch() {
    this.currentPage = 0;
    this.loadProducts(this.searchQuery);
  }

  onSelectionChange(event: Event) {
    const selectedValue = (event.target as HTMLSelectElement).value;
    switch(selectedValue)
    {
      case '1':
        this.sortType = 'ID';
        this.order = 'ASC';
        break;
      case '2':
        this.sortType = 'ID';
        this.order = 'DESC';
        break;
      case '3':
        this.sortType = 'PRICE';
        this.order = 'ASC';
        break;
      case '4':
        this.sortType = 'PRICE';
        this.order = 'DESC';
        break;
      case '5':
        this.sortType = 'RATING';
        this.order = 'ASC';
        break;
      case '6':
        this.sortType = 'RATING';
        this.order = 'DESC';
        break;
      case '7':
        this.sortType = 'NAME';
        this.order = 'ASC';
        break;       
      case '8':
        this.sortType = 'NAME';
        this.order = 'DESC';
        break;
    }

    this.loadProducts(this.searchQuery, this.sortType, this.order);
  }

  openDialog(product?: Product) {
    const dialogRef = this.dialog.open(ProductDialogComponent, {
      width: '600px',
      data: product
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result) {
        if (result.id > 0) {
          this.apiService.updateProduct(result).subscribe(() => {
            this.loadProducts(this.searchQuery, this.sortType, this.order);
          });
       } else {
          this.apiService.addProduct(result).subscribe(() => {
            this.loadProducts(this.searchQuery, this.sortType, this.order);
          });
        }
      }
    });

  }

  // onPageChange(event: any) {
  //   this.loadProducts(this.searchQuery, this.sortType, this.order, event.pageIndex);
  // }

}