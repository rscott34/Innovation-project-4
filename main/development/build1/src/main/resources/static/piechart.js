const items = document.querySelectorAll('.chart-item');
const labels = [], values = [];
const colors = ['#2c3e50','#e74c3c','#3498db','#2ecc71','#f39c12','#9b59b6','#1abc9c','#e67e22'];

items.forEach(item => {
    labels.push(item.dataset.label);
    values.push(parseFloat(item.dataset.value));
});

if (labels.length > 0) {
    new Chart(document.getElementById('ingredientChart'), {
        type: 'pie',
        data: {
            labels: labels,
            datasets: [{
                data: values,
                backgroundColor: colors.slice(0, labels.length)
            }]
        },
        options: {
            plugins: {
                legend: { position: 'bottom' },
                tooltip: {
                    callbacks: {
                        label: ctx => ` ${ctx.label}: ${ctx.parsed}%`
                    }
                }
            }
        }
    });
}